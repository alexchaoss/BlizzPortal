package com.BlizzardArmory.ui.warcraft.account

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.account.Character
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.state.FragmentTag
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Locale


class CharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_character_list, parent, false)) {

    private var avatar: ImageView? = null
    private var faction: ImageView? = null
    private var name: TextView? = null
    private var characterClass: TextView? = null
    private var level: TextView? = null
    private var characterLayout: CardView? = null
    private var gson: Gson? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var fragmentManager: FragmentManager? = null
    private var character: Character? = null
    private var downloaded = false
    private var job: Job? = null

    init {
        avatar = itemView.findViewById(R.id.avatar)
        faction = itemView.findViewById(R.id.faction)
        name = itemView.findViewById(R.id.name)
        characterClass = itemView.findViewById(R.id.character_class)
        level = itemView.findViewById(R.id.level)
        characterLayout = itemView.findViewById(R.id.character_layout)
        gson = GsonBuilder().create()
        EventBus.getDefault().register(this)
        job = CoroutineScope(Dispatchers.Default).launch {
            do {
                delay(3000)
                if (!downloaded) {
                    EventBus.getDefault().post(NetworkEvent(ConnectionStatus.hasNetwork()))
                }
            } while (!ConnectionStatus.hasNetwork())
        }
    }

    fun bind(character: Character, battlenetOAuth2Params: BattlenetOAuth2Params, fragmentManager: FragmentManager) {
        this.battlenetOAuth2Params = battlenetOAuth2Params
        this.fragmentManager = fragmentManager
        this.character = character
        name?.text = character.name
        characterClass?.text = character.playableClass.name
        level?.text = "Level " + character.level
        if (character.faction.type == "HORDE") {
            faction?.setBackgroundResource(R.drawable.horde_logo)
        } else {
            faction?.setBackgroundResource(R.drawable.alliance_logo)
        }
        downloadMedia()
        onClickCharacter(character, "", fragmentManager)
    }

    private fun downloadMedia() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetroClient.getWoWClient(context, cacheTime = 365L)
                .getMedia(character?.name?.lowercase(Locale.getDefault())!!, character?.realm?.slug!!)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val media = response.body()!!
                    onClickCharacter(character!!, gson?.toJson(response.body())!!, fragmentManager!!)
                    downloadAvatar(media, character!!)
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
    }

    private fun downloadAvatar(media: Media?, character: Character) {
        val mediaUrl: String? = if (media == null) {
            "https://render-us.worldofwarcraft.com/character/auchindoun/0/0-main.jpg"
        } else {
            media.assets?.first { it.key == "avatar" }?.value
        }
        val fullURL = mediaUrl + NetworkUtils.NOT_FOUND_URL_AVATAR + character.playableRace.id + "-" + (if (character.gender.type == "MALE") 1 else 0) + ".jpg"

        Glide.with(context.applicationContext).load(fullURL).placeholder(R.drawable.loading_placeholder).into(avatar!!)
        downloaded = true
        job?.cancel()
    }

    private fun onClickCharacter(character: Character, media: String, fragmentManager: FragmentManager) {
        characterLayout?.setOnClickListener {
            val woWNavFragment =
                WoWNavFragment.newInstance(character.name.lowercase(Locale.getDefault()), character.realm.slug, media, NetworkUtils.region)
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            fragmentTransaction.add(R.id.fragment, woWNavFragment, FragmentTag.NAVFRAGMENT.name)
            fragmentTransaction.addToBackStack("wow_nav").commit()
            fragmentManager.executePendingTransactions()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            downloadMedia()
        }
    }

}

package com.BlizzardArmory.ui.ui_warcraft.account

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.account.Character
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.ConnectionStatus
import com.BlizzardArmory.util.events.NetworkEvent
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import java.util.*


class AccountViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
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

    init {
        avatar = itemView.findViewById(R.id.avatar)
        faction = itemView.findViewById(R.id.faction)
        name = itemView.findViewById(R.id.name)
        characterClass = itemView.findViewById(R.id.character_class)
        level = itemView.findViewById(R.id.level)
        characterLayout = itemView.findViewById(R.id.character_layout)
        gson = GsonBuilder().create()
        EventBus.getDefault().register(this)
        GlobalScope.launch {
            do {
                EventBus.getDefault().post(NetworkEvent(ConnectionStatus.hasNetwork()))
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
    }

    private fun downloadMedia() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        val call: Call<Media> = RetroClient.getClient.getMedia(character?.name?.toLowerCase(Locale.ROOT), character?.realm?.slug, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper.accessToken)
        call.enqueue(object : Callback<Media> {
            override fun onResponse(call: Call<Media>, response: retrofit2.Response<Media>) {
                val media: Media? = response.body()
                onClickCharacter(character!!, gson?.toJson(response.body())!!, fragmentManager!!)
                downloadAvatar(media, character!!)

            }

            override fun onFailure(call: Call<Media>, t: Throwable) {
                Log.e("Error", "trace", t)
                val media: Media? = null
                downloadAvatar(media, character!!)
                onClickCharacter(character!!, "", fragmentManager!!)
            }
        })
    }

    private fun downloadAvatar(media: Media?, character: Character) {
        val mediaUrl: String? = if (media == null) {
            "https://render-us.worldofwarcraft.com/character/auchindoun/0/0-main.jpg"
        } else {
            media.assets?.first { it.key == "avatar" }?.value
        }
        val fullURL = mediaUrl + URLConstants.NOT_FOUND_URL_AVATAR + character.playableRace.id + "-" + (if (character.gender.type == "MALE") 1 else 0) + ".jpg"

        Glide.with(context.applicationContext).load(fullURL).placeholder(R.drawable.loading_placeholder).into(avatar!!)
    }

    private fun onClickCharacter(character: Character, media: String, fragmentManager: FragmentManager) {
        characterLayout?.setOnClickListener {
            val woWNavFragment = WoWNavFragment.newInstance(character.name.toLowerCase(Locale.ROOT), character.realm.slug, media, MainActivity.selectedRegion)
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            fragmentTransaction.replace(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
            fragmentTransaction.addToBackStack("wow_nav").commit()
            fragmentManager.executePendingTransactions()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            downloadMedia()
        }
    }

}

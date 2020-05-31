package com.BlizzardArmory.ui.ui_warcraft.account

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.BlizzardArmory
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.favorites.FavoriteCharacter
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.NetworkEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback


class FavoritesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_favorite_character, parent, false)) {

    private var avatar: ImageView? = null
    private var name: TextView? = null
    private var realm: TextView? = null
    private var region: TextView? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var characterLayout: ConstraintLayout? = null
    private var gson: Gson? = null
    private var fragmentManager: FragmentManager? = null

    init {
        avatar = itemView.findViewById(R.id.avatar)
        name = itemView.findViewById(R.id.name)
        realm = itemView.findViewById(R.id.realm)
        region = itemView.findViewById(R.id.region)
        characterLayout = itemView.findViewById(R.id.character_layout)
        gson = GsonBuilder().create()
        EventBus.getDefault().register(this)
        GlobalScope.launch {
            do {
                EventBus.getDefault().post(NetworkEvent(BlizzardArmory.hasNetwork()))
            } while (!BlizzardArmory.hasNetwork())
        }
    }

    fun bind(character: FavoriteCharacter, fragmentManager: FragmentManager, bnOAuth2Params: BnOAuth2Params) {
        this.bnOAuth2Params = bnOAuth2Params

    }

    private fun downloadMedia() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)

        val call: Call<Media> = RetroClient.getClient.getMedia(name?.text.toString(), realm?.text.toString(), MainActivity.locale, region?.text.toString(), bnOAuth2Helper.accessToken)
        call.enqueue(object : Callback<Media> {
            override fun onResponse(call: Call<Media>, response: retrofit2.Response<Media>) {
                val media: Media? = response.body()
                onClickCharacter(gson?.toJson(response.body())!!, fragmentManager!!)
                downloadAvatar(media)

            }

            override fun onFailure(call: Call<Media>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                val media: Media? = null
                downloadAvatar(media)
                onClickCharacter("", fragmentManager!!)
            }
        })
    }

    private fun downloadAvatar(media: Media?) {
        val mediaUrl: String? = if (media == null) {
            "https://render-us.worldofwarcraft.com/character/auchindoun/0/0-main.jpg"
        } else {
            media.avatarUrl
        }
        val fullURL = mediaUrl + URLConstants.NOT_FOUND_URL_AVATAR + 0 + "-" + 0 + ".jpg"

        Picasso.get().load(fullURL).placeholder(R.drawable.loading_placeholder).into(avatar)
    }

    private fun onClickCharacter(media: String, fragmentManager: FragmentManager) {
        characterLayout?.setOnClickListener {
            val woWNavFragment = WoWNavFragment.newInstance(name?.text.toString(), realm?.text.toString(), media, MainActivity.selectedRegion)
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            fragmentTransaction.replace(R.id.nav_fragment, woWNavFragment, "NAV_FRAGMENT")
            fragmentTransaction.addToBackStack("NAV_FRAGMENT").commit()
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

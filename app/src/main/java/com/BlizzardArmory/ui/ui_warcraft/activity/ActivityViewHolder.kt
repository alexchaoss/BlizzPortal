package com.BlizzardArmory.ui.ui_warcraft.activity

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.RequestQueueSingleton
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.WoWNavFragment
import com.BlizzardArmory.warcraft.account.Character
import com.BlizzardArmory.warcraft.media.Media
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.util.*


class ActivityViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_activity_character_list, parent, false)) {

    private var avatar: ImageView? = null
    private var faction: ImageView? = null
    private var name: TextView? = null
    private var characterClass: TextView? = null
    private var level: TextView? = null
    private var characterLayout: ConstraintLayout? = null

    init {
        avatar = itemView.findViewById(R.id.avatar)
        faction = itemView.findViewById(R.id.faction)
        name = itemView.findViewById(R.id.name)
        characterClass = itemView.findViewById(R.id.character_class)
        level = itemView.findViewById(R.id.level)
        characterLayout = itemView.findViewById(R.id.character_layout)
    }

    fun bind(character: Character, bnOAuth2Params: BnOAuth2Params, fragmentManager: FragmentManager) {
        name?.text = character.name
        characterClass?.text = character.playableClass.name
        level?.text = "Level " + character.level
        if (character.faction.name == "Horde") {
            faction?.setBackgroundResource(R.drawable.horde_logo)
        } else {
            faction?.setBackgroundResource(R.drawable.alliance_logo)
        }

        downloadMedia(character, bnOAuth2Params, fragmentManager)

    }

    private fun downloadMedia(character: Character, bnOAuth2Params: BnOAuth2Params, fragmentManager: FragmentManager) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)

        val jsonRequestMedia = JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI(MainActivity.selectedRegion.toLowerCase())
                + URLConstants.MEDIA_QUERY.replace("zone", MainActivity.selectedRegion.toLowerCase()).replace("realm", character.realm.slug)
                .replace("charactername", character.name.toLowerCase(Locale.ROOT)) + bnOAuth2Helper.accessToken, null,
                Response.Listener { response: JSONObject ->
                    val gsonMedia = GsonBuilder().create()
                    val media: Media = gsonMedia.fromJson(response.toString(), Media::class.java)

                    onClickCharacter(character, response.toString(), fragmentManager)
                    downloadAvatar(media, character)

                }, Response.ErrorListener {
            val media: Media? = null
            downloadAvatar(media, character)
            onClickCharacter(character, "", fragmentManager)
        })
        RequestQueueSingleton.getInstance(context).addToRequestQueue(jsonRequestMedia)
    }

    private fun downloadAvatar(media: Media?, character: Character) {
        val mediaUrl: String?
        if (media == null) {
            mediaUrl = "https://render-us.worldofwarcraft.com/character/auchindoun/0/0-main.jpg"
        } else {
            mediaUrl = media.avatarUrl
        }
        val fullURL = mediaUrl + URLConstants.NOT_FOUND_URL_AVATAR + character.playableRace.id + "-" + (if (character.gender.type == "MALE") 1 else 0) + ".jpg"

        Picasso.get().load(fullURL).into(avatar)
    }

    private fun onClickCharacter(character: Character, media: String, fragmentManager: FragmentManager) {
        characterLayout?.setOnClickListener {
            val woWNavFragment = WoWNavFragment.newInstance(character.name.toLowerCase(Locale.ROOT), character.realm.slug, media, MainActivity.selectedRegion)
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            fragmentTransaction.replace(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
            fragmentTransaction.addToBackStack("NAV_FRAGMENT").commit()
            fragmentManager.executePendingTransactions()
        }
    }

}

private operator fun String?.invoke(s: String) {

}

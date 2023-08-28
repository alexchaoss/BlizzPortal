package com.BlizzardArmory.ui.diablo.diablo3.account

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.diablo3.account.Hero
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.util.events.D3CharacterEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus


class D3CharacterFrameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_character_frame, parent, false)) {

    var frame: ImageView? = null
    var portrait: ImageView? = null
    var season: ImageView? = null
    var name: TextView? = null
    var eliteKills: TextView? = null
    var level: TextView? = null

    init {
        frame = itemView.findViewById(R.id.frame)
        portrait = itemView.findViewById(R.id.portrait)
        season = itemView.findViewById(R.id.season)
        name = itemView.findViewById(R.id.name)
        eliteKills = itemView.findViewById(R.id.elite_kills)
        level = itemView.findViewById(R.id.level)
    }

    fun bind(hero: Hero) {
        if (hero.hardcore) {
            frame?.setImageResource(R.drawable.d3_character_frame_hardcore)
            level?.setTextColor(Color.parseColor("#b00000"))
        } else {
            frame?.setImageResource(R.drawable.d3_character_frame)
            level?.setTextColor(Color.parseColor("#a99877"))
        }

        if (hero.seasonal) {
            season?.visibility = View.VISIBLE
        }

        Glide.with(itemView).load(NetworkUtils.getD3Asset(getGender(hero))).into(portrait!!)

        name?.text = hero.name
        eliteKills?.text = "${hero.kills.elites} Elite Kills"
        level?.text = "${hero.level}"

        itemView.setOnClickListener {
            EventBus.getDefault().post(D3CharacterEvent(hero))
        }
    }

    private fun getGender(hero: Hero): String {
        var imageName = ""
        when (hero.classSlug) {
            "barbarian" -> if (hero.gender == 0) {
                imageName = "barb_male"
            } else if (hero.gender == 1) {
                imageName = "barb_female"
            }
            "wizard" -> if (hero.gender == 0) {
                imageName = "wizard_male"
            } else if (hero.gender == 1) {
                imageName = "wizard_female"
            }
            "demon-hunter" -> if (hero.gender == 0) {
                imageName = "dh_male"
            } else if (hero.gender == 1) {
                imageName = "dh_female"
            }
            "witch-doctor" -> if (hero.gender == 0) {
                imageName = "wd_male"
            } else if (hero.gender == 1) {
                imageName = "wd_female"
            }
            "necromancer" -> if (hero.gender == 0) {
                imageName = "necro_male"
            } else if (hero.gender == 1) {
                imageName = "necro_female"
            }
            "monk" -> if (hero.gender == 0) {
                imageName = "monk_male"
            } else if (hero.gender == 1) {
                imageName = "monk_female"
            }
            "crusader" -> if (hero.gender == 0) {
                imageName = "crusader_male"
            } else if (hero.gender == 1) {
                imageName = "crusader_female"
            }
        }
        return imageName
    }

}

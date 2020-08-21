package com.BlizzardArmory.ui.ui_diablo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.model.diablo.account.FallenHero
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


class D3DeadCharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_character_dead, parent, false)) {

    var frame: ImageView? = null
    var name: TextView? = null
    var date: TextView? = null
    var level: TextView? = null

    init {
        frame = itemView.findViewById(R.id.frame)
        name = itemView.findViewById(R.id.name)
        date = itemView.findViewById(R.id.date)
        level = itemView.findViewById(R.id.level)
    }

    fun bind(fallenHero: FallenHero) {
        Glide.with(itemView).load(URLConstants.getD3Asset(getFallenHeroFrame(fallenHero))).into(frame!!)

        val format = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
        val dateFormatted = Date(fallenHero.death.time.toLong() * 1000L)
        date?.text = format.format(dateFormatted)

        name?.text = fallenHero.name

        level?.text = "${fallenHero.level}"
    }

    private fun getFallenHeroFrame(fallenHero: FallenHero): String {
        var imageName = ""
        when (fallenHero.class_) {
            "barbarian" -> if (fallenHero.gender == 0) {
                imageName = "male_barb_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_barb_dead"
            }
            "wizard" -> if (fallenHero.gender == 0) {
                imageName = "male_wizard_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_wizard_dead"
            }
            "demon-hunter" -> if (fallenHero.gender == 0) {
                imageName = "male_dh_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_dh_dead"
            }
            "witch-doctor" -> if (fallenHero.gender == 0) {
                imageName = "male_wd_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_wd_dead"
            }
            "necromancer" -> if (fallenHero.gender == 0) {
                imageName = "male_necromancer_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_necromancer_dead"
            }
            "monk" -> if (fallenHero.gender == 0) {
                imageName = "male_monk_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_monk_dead"
            }
            "crusader" -> if (fallenHero.gender == 0) {
                imageName = "male_crusader_dead"
            } else if (fallenHero.gender == 1) {
                imageName = "female_crusader_dead"
            }
        }
        return imageName
    }

}

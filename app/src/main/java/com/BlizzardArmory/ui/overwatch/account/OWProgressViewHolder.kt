package com.BlizzardArmory.ui.overwatch.account

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.account.topheroes.TopHero
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.apache.commons.lang3.StringUtils
import java.util.*


class OWProgressViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.ow_hero_view, parent, false)) {

    private val TIME_PLAYED = "Time Played"
    private val GAMES_WON = "Games Won"
    private val WEAPON_ACCURACY = "Weapon Accuracy"
    private val ELIMINATIONS_PER_LIFE = "Eliminations per Life"
    private val MULTIKILL_BEST = "Multikill - Best"
    private val OBJECTIVE_KILLS = "Objective Kills"

    var icon: ImageView? = null
    var name: TextView? = null
    var data: TextView? = null
    var progress: ProgressBar? = null

    init {
        icon = itemView.findViewById(R.id.hero_icon)
        name = itemView.findViewById(R.id.hero_name)
        data = itemView.findViewById(R.id.data)
        progress = itemView.findViewById(R.id.ow_progress)
    }

    fun bind(hero: TopHero, sortedBy: String, topHero: TopHero) {
        setBackgroundColor(hero.javaClass.simpleName)
        setName(hero)
        downloadIcon(hero)
        chooseCategory(sortedBy, hero, topHero)
    }

    private fun setName(hero: TopHero) {
        val tempName: String = when (hero.javaClass.simpleName) {
            "WreckingBall" -> {
                "Wrecking Ball "
            }
            "Dva" -> {
                "D.Va "
            }
            "Soldier76" -> {
                "Soldier: 76 "
            }
            else -> {
                hero.javaClass.simpleName + " "
            }
        }
        name?.text = tempName
    }

    private fun downloadIcon(hero: TopHero) {

        Glide.with(itemView)
            .load(NetworkUtils.getOWIconImage(hero.javaClass.simpleName.lowercase(Locale.getDefault())))
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    icon?.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun chooseCategory(sortedBy: String, hero: TopHero, topHero: TopHero) {
        when (sortedBy) {
            TIME_PLAYED -> {
                progress?.max = getSeconds(topHero)
                progress?.progress = getSeconds(hero)
                data?.text = hero.timePlayed
            }
            GAMES_WON -> {
                progress?.max = topHero.gamesWon.toInt()
                progress?.progress = hero.gamesWon.toInt()
                data?.text = hero.gamesWon.toInt().toString()
            }
            WEAPON_ACCURACY -> {
                progress?.max = topHero.weaponAccuracy.toInt()
                progress?.progress = hero.weaponAccuracy.toInt()
                val tempData: String = hero.weaponAccuracy.toInt().toString() + "%"
                data!!.text = tempData
            }
            ELIMINATIONS_PER_LIFE -> {
                progress?.max = topHero.eliminationsPerLife.toInt()
                progress?.progress = hero.eliminationsPerLife.toInt()
                data?.text = hero.eliminationsPerLife.toString()
            }
            MULTIKILL_BEST -> {
                progress?.max = topHero.multiKillBest.toInt()
                progress?.progress = hero.multiKillBest.toInt()
                data?.text = hero.multiKillBest.toInt().toString()
            }
            OBJECTIVE_KILLS -> {
                progress?.max = topHero.objectiveKills.toInt()
                progress?.progress = hero.objectiveKills.toInt()
                data?.text = hero.objectiveKills.toInt().toString()
            }
        }
    }

    private fun getSeconds(hero: TopHero): Int {
        var secondsHero1 = 0
        if (StringUtils.countMatches(hero.timePlayed, ":") == 2) {
            secondsHero1 += hero.timePlayed?.substring(0, hero.timePlayed!!.indexOf(":"))!!
                .toInt() * 3600
            secondsHero1 += hero.timePlayed?.substring(hero.timePlayed!!.indexOf(":") + 1, hero.timePlayed!!.lastIndexOf(":"))!!
                .toInt() * 60
            secondsHero1 += hero.timePlayed?.substring(hero.timePlayed!!.lastIndexOf(":") + 1)!!
                .toInt()
        } else if (StringUtils.countMatches(hero.timePlayed, ":") == 1) {
            secondsHero1 += hero.timePlayed?.substring(0, hero.timePlayed!!.indexOf(":"))
                ?.toInt()!! * 60
            secondsHero1 += hero.timePlayed?.substring(hero.timePlayed!!.lastIndexOf(":") + 1)!!
                .toInt()
        } else {
            secondsHero1 = hero.timePlayed?.toInt()!!
        }
        return secondsHero1
    }

    private fun setBackgroundColor(topCharacterName: String) {
        val color = when (topCharacterName) {
            "Ana" -> Color.parseColor("#9c978a")
            "Ashe" -> Color.parseColor("#b3a05f")
            "Baptiste" -> Color.parseColor("#2892a8")
            "Bastion" -> Color.parseColor("#24f9f8")
            "Brigitte" -> Color.parseColor("#efb016")
            "DVa" -> Color.parseColor("#ee4bb5")
            "Echo" -> Color.parseColor("#89c8ff")
            "Doomfist" -> Color.parseColor("#762c21")
            "Genji" -> Color.parseColor("#abe50b")
            "Hanzo" -> Color.parseColor("#837c46")
            "Junkrat" -> Color.parseColor("#fbd73a")
            "Lúcio" -> Color.parseColor("#aaf531")
            "Mccree" -> Color.parseColor("#c23f46")
            "Mei" -> Color.parseColor("#87d7f6")
            "Mercy" -> Color.parseColor("#fcd849")
            "Moira" -> Color.parseColor("#7112f4")
            "Orisa" -> Color.parseColor("#468c43")
            "Pharah" -> Color.parseColor("#3461a4")
            "Reaper" -> Color.parseColor("#333333")
            "Reinhardt" -> Color.parseColor("#929da3")
            "Roadhog" -> Color.parseColor("#54515a")
            "Sigma" -> Color.parseColor("#33bbaa")
            "Sojourn" -> Color.parseColor("#ffffff")
            "Soldier76" -> Color.parseColor("#525d9b")
            "Sombra" -> Color.parseColor("#9762ec")
            "Symmetra" -> Color.parseColor("#3e90b5")
            "Torbjörn" -> Color.parseColor("#b04a33")
            "Tracer" -> Color.parseColor("#ffcf35")
            "Widowmaker" -> Color.parseColor("#af5e9e")
            "Winston" -> Color.parseColor("#595959")
            "WreckingBall" -> Color.parseColor("#4a575f")
            "Zarya" -> Color.parseColor("#ff73c1")
            "Zenyatta" -> Color.parseColor("#e1c931")
            else -> 0
        }
        progress?.progressTintList = ColorStateList.valueOf(color)
    }
}

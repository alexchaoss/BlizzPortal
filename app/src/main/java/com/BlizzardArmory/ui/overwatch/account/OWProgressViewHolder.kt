package com.BlizzardArmory.ui.overwatch.account

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
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
import org.apache.commons.lang3.StringUtils
import java.util.Locale
import kotlin.math.roundToInt


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
            "JunkerQueen" -> {
                "Junker Queen "
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
        Glide.with(itemView).load(NetworkUtils.getOWIconImage(hero.javaClass.simpleName.lowercase(Locale.getDefault()))).into(icon!!)
    }

    private fun chooseCategory(sortedBy: String, hero: TopHero, topHero: TopHero) {
        when (sortedBy) {
            TIME_PLAYED -> {
                progress?.max = getSeconds(topHero)
                progress?.progress = minTwoPercent(getSeconds(topHero), getSeconds(hero))
                data?.text = hero.timePlayed
            }
            GAMES_WON -> {
                progress?.max = topHero.gamesWon.toInt()
                progress?.progress = minTwoPercent(topHero.gamesWon.toInt(), hero.gamesWon.toInt())
                data?.text = hero.gamesWon.toInt().toString()
            }
            WEAPON_ACCURACY -> {
                progress?.max = topHero.weaponAccuracy.toInt()
                progress?.progress = minTwoPercent(topHero.weaponAccuracy.toInt(), hero.weaponAccuracy.toInt())
                val tempData: String = hero.weaponAccuracy.toInt().toString() + "%"
                data!!.text = tempData
            }
            ELIMINATIONS_PER_LIFE -> {
                progress?.max = topHero.eliminationsPerLife.toInt()
                progress?.progress = minTwoPercent(topHero.eliminationsPerLife.toInt(), hero.eliminationsPerLife.toInt())
                data?.text = hero.eliminationsPerLife.toString()
            }
            MULTIKILL_BEST -> {
                progress?.max = topHero.multiKillBest.toInt()
                progress?.progress = minTwoPercent(topHero.multiKillBest.toInt(), hero.multiKillBest.toInt())
                data?.text = hero.multiKillBest.toInt().toString()
            }
            OBJECTIVE_KILLS -> {
                progress?.max = topHero.objectiveKills.toInt()
                progress?.progress = minTwoPercent(topHero.objectiveKills.toInt(), hero.objectiveKills.toInt())
                data?.text = hero.objectiveKills.toInt().toString()
            }
        }
    }

    private fun minTwoPercent(top: Int, current: Int): Int {
        return if (current < (top * 0.02).roundToInt()) (top * 0.02).roundToInt() else current
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
            "Ana" -> Color.parseColor("#859ABE")
            "Ashe" -> Color.parseColor("#7E7D7E")
            "Baptiste" -> Color.parseColor("#70C1D6")
            "Bastion" -> Color.parseColor("#91A18C")
            "Brigitte" -> Color.parseColor("#A07873")
            "DVa" -> Color.parseColor("#F9A4D2")
            "Echo" -> Color.parseColor("#89c8ff")
            "Doomfist" -> Color.parseColor("#986A62")
            "Genji" -> Color.parseColor("#A8F858")
            "Hanzo" -> Color.parseColor("#C8C399")
            "JunkerQueen" -> Color.parseColor("#8EBDDE")
            "Junkrat" -> Color.parseColor("#F6CA67")
            "Kiriko" -> Color.parseColor("#E8C6EF")
            "Lifeweaver" -> Color.parseColor("#E6C0D0")
            "Lúcio" -> Color.parseColor("#99D568")
            "Cassidy" -> Color.parseColor("#C17171")
            "Mei" -> Color.parseColor("#83BAF3")
            "Mercy" -> Color.parseColor("#F7F3C8")
            "Moira" -> Color.parseColor("#A987EC")
            "Orisa" -> Color.parseColor("#609F5B")
            "Pharah" -> Color.parseColor("#8FD1FD")
            "Ramattra" -> Color.parseColor("#A78DD9")
            "Reaper" -> Color.parseColor("#935769")
            "Reinhardt" -> Color.parseColor("#A5B0B3")
            "Roadhog" -> Color.parseColor("#C69E6A")
            "Sigma" -> Color.parseColor("#A5B0B3")
            "Sojourn" -> Color.parseColor("#E17F74")
            "Soldier76" -> Color.parseColor("#CBD5E3")
            "Sombra" -> Color.parseColor("#9762ec")
            "Symmetra" -> Color.parseColor("#A2CADA")
            "Torbjörn" -> Color.parseColor("#CE8781")
            "Tracer" -> Color.parseColor("#E6A658")
            "Widowmaker" -> Color.parseColor("#B07FB5")
            "Winston" -> Color.parseColor("#B1B3C8")
            "WreckingBall" -> Color.parseColor("#E8A55E")
            "Zarya" -> Color.parseColor("#F493C3")
            "Zenyatta" -> Color.parseColor("#F9F092")
            else -> 0
        }
        progress?.progressTintList = ColorStateList.valueOf(color)
    }
}

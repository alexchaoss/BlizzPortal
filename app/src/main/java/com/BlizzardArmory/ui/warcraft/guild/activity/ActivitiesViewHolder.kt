package com.BlizzardArmory.ui.warcraft.guild.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.guild.activity.Activities
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ActivitiesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_activity_list, parent, false)) {

    var info: TextView? = null

    init {
        info = itemView.findViewById(R.id.info)
    }

    fun bind(activity: Activities /*roster: Roster*/) {
        var activityInfo = ""
        val stamp = Timestamp(activity.timestamp)
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.format(Date(stamp.time))
        if (activity.activity.type == "ENCOUNTER") {
            activityInfo += "${activity.encounterCompleted.mode.name} ${activity.encounterCompleted.encounter.name} was defeated on $date"
        } else {
            activityInfo += "<font color=#ffffff>${activity.characterAchievement.character.name}</font>"
            activityInfo += context.resources.getString(R.string.hascompleted)
            activityInfo += "<font color=#f8b700>${activity.characterAchievement.achievement.name}</font>"
            activityInfo += " on $date"
        }

        info?.text = HtmlCompat.fromHtml(activityInfo, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
        //getCharacterNameColor(activity.character_achievement.character.id, roster)
    }

    /*fun getCharacterNameColor(id: Long, roster: Roster): String {
        when(roster.members.find { it.character.id == id }?.character?.playable_class?.id){
            1 -> "#C79C6E"
            2 -> "#F58CBA"
            3 -> "#ABD473"
            4 -> "#FFF569"
            5 -> "#FFFFFF"
            6 -> "#C41F3B"
            7 -> "#0070DE"
            8 -> "#69CCF0"
            9 -> "#9482C9"
            10 -> "#00FF96"
            11 -> "#FF7D0A"
            12 -> "#A330C9"
        }
    }*/
}
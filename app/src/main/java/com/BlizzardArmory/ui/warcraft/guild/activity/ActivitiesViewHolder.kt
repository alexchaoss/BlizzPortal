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

    fun bind(activity: Activities) {
        var activityInfo = ""
        val stamp = Timestamp(activity.timestamp)
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.format(Date(stamp.time))
        if (activity.activity.type == "ENCOUNTER") {
            activityInfo += "${activity.encounterCompleted.mode.name} ${activity.encounterCompleted.encounter.name} ${context.resources.getString(R.string.defeated)} $date"
        } else {
            activityInfo += "<font color=#ffffff>${activity.characterAchievement.character.name}</font>"
            activityInfo += context.resources.getString(R.string.hascompleted)
            activityInfo += "<font color=#f8b700>${activity.characterAchievement.achievement.name}</font>"
            activityInfo += " on $date"
        }

        info?.text = HtmlCompat.fromHtml(activityInfo, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
    }
}
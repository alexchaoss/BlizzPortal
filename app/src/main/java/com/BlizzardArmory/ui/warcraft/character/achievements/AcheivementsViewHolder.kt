package com.BlizzardArmory.ui.warcraft.character.achievements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.bumptech.glide.Glide
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class AcheivementsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_achievement_list, parent, false)) {

    var icon: ImageView? = null
    var name: TextView? = null
    var description: TextView? = null
    var points: TextView? = null
    var date: TextView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
        name = itemView.findViewById(R.id.name)
        description = itemView.findViewById(R.id.description)
        points = itemView.findViewById(R.id.points)
        date = itemView.findViewById(R.id.completed_date)
    }

    fun bind(detailedAchievement: DetailedAchievement, achievements: List<Achievement>) {
        Glide.with(itemView).load(detailedAchievement.icon).into(icon!!)
        if (detailedAchievement.is_account_wide) {
            name?.setTextColor(Color.parseColor("#0081ff"))
        }
        name?.text = detailedAchievement.name
        description?.text = detailedAchievement.description
        points?.text = detailedAchievement.points.toString()
        val timestamp = achievements.find { it.id == detailedAchievement.id }!!.completed_timestamp
        val stamp = Timestamp(timestamp)
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        date?.text = dateFormat.format(Date(stamp.time))
        if (timestamp == 0L) {
            itemView.alpha = 0.3F
        } else {
            itemView.alpha = 1F
        }
    }
}
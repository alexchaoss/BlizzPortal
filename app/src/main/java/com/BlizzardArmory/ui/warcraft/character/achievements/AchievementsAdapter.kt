package com.BlizzardArmory.ui.warcraft.character.achievements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement

class AchievementsAdapter(private val list: List<DetailedAchievement>, private val achievements: List<Achievement>)
    : RecyclerView.Adapter<AcheivementsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcheivementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AcheivementsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AcheivementsViewHolder, position: Int) {
        if (position % 2 != 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#15FFFFFF"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#00000000"))
        }
        val detailedAchievement: DetailedAchievement = list[position]
        holder.bind(detailedAchievement, achievements)
    }

    override fun getItemCount(): Int = list.size

}
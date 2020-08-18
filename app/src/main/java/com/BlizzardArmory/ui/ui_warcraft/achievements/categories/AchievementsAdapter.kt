package com.BlizzardArmory.ui.ui_warcraft.progress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.achievements.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievement

class AchievementsAdapter(private val list: List<DetailedAchievement>, private val achievements: List<Achievement>, private val locale: String)
    : RecyclerView.Adapter<AcheivementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcheivementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AcheivementsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AcheivementsViewHolder, position: Int) {
        val detailedAchievement: DetailedAchievement = list[position]
        holder.bind(detailedAchievement, locale, achievements, position)
    }

    override fun getItemCount(): Int = list.size

}
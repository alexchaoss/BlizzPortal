package com.BlizzardArmory.ui.warcraft.guild.achievements

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.model.warcraft.guild.achievements.Achievements

class AchievementsAdapter(private val list: List<DetailedAchievement>, private val achievements: List<Achievements>) :
    RecyclerView.Adapter<AcheivementsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcheivementsViewHolder {
        Log.i("Achiev size", list.size.toString())
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
package com.BlizzardArmory.ui.warcraft.guild.achievements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.model.warcraft.achievements.categories.Category
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.model.warcraft.guild.achievements.Achievements

class CategoriesAdapter(private val list: List<Category>, private val locale: String, private val faction: String, private val mappedAchievements: Map<Long, List<DetailedAchievement>?>, private val achievements: List<Achievements>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category: Category = list[position]
        holder.bind(category, faction, mappedAchievements, achievements)
    }

    override fun getItemCount(): Int = list.size

}
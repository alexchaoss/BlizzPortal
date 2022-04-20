package com.BlizzardArmory.ui.warcraft.character.achievements

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.achievements.categories.Category
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievement
import com.BlizzardArmory.model.warcraft.achievements.custom.DetailedAchievement
import com.BlizzardArmory.util.events.ParentCategoryEvent
import com.BlizzardArmory.util.events.SubCategoryEvent
import org.greenrobot.eventbus.EventBus

class CategoriesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_achievement_category, parent, false)) {

    var progressBar: ProgressBar? = null
    var categoryName: TextView? = null
    var categoryPoints: TextView? = null
    var percent: TextView? = null
    var layout: ConstraintLayout? = null

    init {
        progressBar = itemView.findViewById(R.id.achiev_progress)
        categoryName = itemView.findViewById(R.id.category)
        categoryPoints = itemView.findViewById(R.id.achiev_points)
        percent = itemView.findViewById(R.id.percent)
        layout = itemView.findViewById(R.id.layout)
    }

    fun bind(category: Category, faction: String, mappedAchievements: Map<Long, List<DetailedAchievement>?>, achievements: List<Achievement>) {
        val currentPoints: Int

        if (category.parentCategoryId == null && category.id != 92L) {
            layout?.setOnClickListener {
                EventBus.getDefault().post(ParentCategoryEvent(category.id))
            }
        } else {
            layout?.setOnClickListener {
                EventBus.getDefault().post(SubCategoryEvent(category.id))
            }
        }

        if (category.parentCategoryId == null && category.id != 92L) {
            currentPoints = mappedAchievements.values.sumOf { v ->
                v?.filter {
                    it.parent_category_id == category.id &&
                            (achievements.find { ac -> ac.id == it.id }?.completed_timestamp != 0L)
                }?.map { it.points }?.sumOf { it }!!
            }
        } else {
            currentPoints = mappedAchievements[category.id]?.filter { achievements.find { ac -> ac.id == it.id }?.completed_timestamp != 0L }
                ?.sumOf { it.points }!!
        }

        progressBar?.max = 100

        categoryName?.text = category.name
        categoryPoints?.text = currentPoints.toString()
        if ((category.alliancePoints != 0 || category.hordePoints != 0)) {
            if (faction == "Horde") {
                percent?.text = "${((currentPoints * 100) / category.hordePoints)}%"
                progressBar?.progress = ((currentPoints * 100) / category.hordePoints)
            } else {
                percent?.text = "${((currentPoints * 100) / category.alliancePoints)}%"
                progressBar?.progress = ((currentPoints * 100) / category.alliancePoints)
            }
        } else {
            percent?.text = "-"
        }

    }
}
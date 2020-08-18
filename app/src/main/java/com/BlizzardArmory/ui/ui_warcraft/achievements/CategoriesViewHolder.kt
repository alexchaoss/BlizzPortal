package com.BlizzardArmory.ui.ui_warcraft.achievements

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.achievements.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.categories.Category
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

    fun bind(category: Category, locale: String, faction: String, mappedAchievements: Map<Long, List<DetailedAchievement>>) {
        var currentPoints = 0

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
            currentPoints = mappedAchievements.values.sumBy { v -> v.filter { it.parent_category_id == category.id }.map { it.points }.sumBy { it } }
        } else {
            currentPoints = mappedAchievements[category.id]?.sumBy { it.points }!!
        }

        Log.i("points", currentPoints.toString())
        progressBar?.max = 100

        /*var name = category.name.substring(category.name.indexOf("en_US"))
        name = name.substring(9, name.indexOf("es_ES") - 4)*/
        //getLocale(locale, category)
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

    /*private fun getLocale(locale: String, category: Category){
        when(locale){
            "de_DE"-> categoryName?.text = category.name.name.deDE
            "en_GB"->categoryName?.text = category.name.name.enGB
            "en_US"-> categoryName?.text = category.name.name.enUS
            "es_ES"-> categoryName?.text = category.name.name.esES
            "es_MX"-> categoryName?.text = category.name.name.esMX
            "fr_FR"-> categoryName?.text = category.name.name.frFR
            "it_IT"-> categoryName?.text = category.name.name.itIT
            "ko_KR"-> categoryName?.text = category.name.name.koKR
            "pt_BR"-> categoryName?.text = category.name.name.ptBR
            "ru_RU"-> categoryName?.text = category.name.name.ruRU
            "zh_CN"-> categoryName?.text = category.name.name.zhCN
            "zh_TW"-> categoryName?.text = category.name.name.zhTW
        }
    }*/

}
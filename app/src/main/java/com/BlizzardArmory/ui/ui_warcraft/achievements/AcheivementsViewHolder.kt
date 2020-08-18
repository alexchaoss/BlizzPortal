package com.BlizzardArmory.ui.ui_warcraft.achievements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.achievements.DetailedAchievement
import com.BlizzardArmory.model.warcraft.achievements.characterachievements.Achievement
import com.squareup.picasso.Picasso
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

    fun bind(detailedAchievement: DetailedAchievement, locale: String, achievements: List<Achievement>) {
        Picasso.get().load(detailedAchievement.icon).into(icon)
        if (detailedAchievement.is_account_wide) {
            name?.setTextColor(Color.parseColor("#0081ff"))
        }
        name?.text = detailedAchievement.name
        description?.text = detailedAchievement.description
        points?.text = detailedAchievement.points.toString()
        val stamp = Timestamp(achievements.find { it.id == detailedAchievement.id }!!.completed_timestamp)
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        date?.text = dateFormat.format(Date(stamp.time))
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
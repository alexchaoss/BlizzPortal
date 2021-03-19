package com.BlizzardArmory.model.warcraft.guild.achievements

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class AchievementsInformation(

    @SerializedName("_links") val _links: Links,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("total_quantity") val total_quantity: Int,
    @SerializedName("total_points") val total_points: Int,
    @SerializedName("achievements") val achievements: List<Achievements>,
    @SerializedName("category_progress") val category_progresses: List<CategoryProgress>,
    @SerializedName("recent_events") val recent_events: List<RecentEvents>
)
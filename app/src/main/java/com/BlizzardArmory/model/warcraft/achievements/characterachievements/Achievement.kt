package com.BlizzardArmory.model.warcraft.achievements.characterachievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Achievement(

    @SerializedName("id") val id: Long,
    @SerializedName("achievement") val achievementInfo: AchievementInfo,
    @SerializedName("criteria") val criteria: Criteria,
    @SerializedName("completed_timestamp") val completed_timestamp: Long
)
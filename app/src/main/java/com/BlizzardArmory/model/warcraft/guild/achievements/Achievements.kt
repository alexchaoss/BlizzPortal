package com.BlizzardArmory.model.warcraft.guild.achievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Achievements(

    @SerializedName("id") val id: Long,
    @SerializedName("achievement") val achievement: Achievement,
    @SerializedName("criteria") val criteria: Criteria,
    @SerializedName("completed_timestamp") val completed_timestamp: Long
)
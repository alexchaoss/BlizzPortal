package com.BlizzardArmory.model.warcraft.guild.achievements

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class RecentEvents(

    @SerializedName("achievement") val achievement: Achievement,
    @SerializedName("timestamp") val timestamp: Long
)
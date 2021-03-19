package com.BlizzardArmory.model.warcraft.guild.achievements

import com.google.gson.annotations.SerializedName


data class RecentEvents(

    @SerializedName("achievement") val achievement: Achievement,
    @SerializedName("timestamp") val timestamp: Long
)
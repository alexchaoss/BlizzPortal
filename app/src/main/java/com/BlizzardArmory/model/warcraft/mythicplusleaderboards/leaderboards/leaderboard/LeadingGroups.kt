package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.google.gson.annotations.SerializedName

data class LeadingGroups(

    @SerializedName("ranking") val ranking: Int,
    @SerializedName("duration") val duration: Long,
    @SerializedName("completed_timestamp") val completed_timestamp: Long,
    @SerializedName("keystone_level") val keystone_levelstone_level: Int,
    @SerializedName("members") val members: List<Members>
)
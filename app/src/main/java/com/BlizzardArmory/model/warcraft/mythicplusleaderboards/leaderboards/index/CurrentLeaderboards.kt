package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.index

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class CurrentLeaderboards(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)
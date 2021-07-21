package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class KeystoneAffix(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
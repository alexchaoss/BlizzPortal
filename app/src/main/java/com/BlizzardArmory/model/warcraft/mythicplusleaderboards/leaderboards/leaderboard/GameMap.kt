package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GameMap(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
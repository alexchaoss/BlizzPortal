package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.google.gson.annotations.SerializedName

data class GameMap(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
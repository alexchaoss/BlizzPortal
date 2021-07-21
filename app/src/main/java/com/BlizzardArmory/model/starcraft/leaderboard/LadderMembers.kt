package com.BlizzardArmory.model.starcraft.leaderboard

import com.google.gson.annotations.SerializedName


data class LadderMembers(

    @SerializedName("character") val character: Character,
    @SerializedName("joinTimestamp") val joinTimestamp: Int,
    @SerializedName("points") val points: Int,
    @SerializedName("wins") val wins: Int,
    @SerializedName("losses") val losses: Int,
    @SerializedName("highestRank") val highestRank: Int,
    @SerializedName("previousRank") val previousRank: Int,
    @SerializedName("favoriteRaceP1") val favoriteRaceP1: String
)
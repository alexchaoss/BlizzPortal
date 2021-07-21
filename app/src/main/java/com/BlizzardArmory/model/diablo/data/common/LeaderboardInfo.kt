package com.BlizzardArmory.model.diablo.data.common

import com.google.gson.annotations.SerializedName


data class LeaderboardInfo(

    @SerializedName("team_size") val team_size: Int,
    @SerializedName("ladder") val ladder: Ladder,
    @SerializedName("hardcore") val hardcore: Boolean = false,
    @SerializedName("hero_class_string") val hero_class_string: String
)
package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName

data class Profile(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Realm
)
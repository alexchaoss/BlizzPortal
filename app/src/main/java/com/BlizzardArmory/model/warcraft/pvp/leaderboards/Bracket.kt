package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import com.google.gson.annotations.SerializedName

data class Bracket(

    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String
)
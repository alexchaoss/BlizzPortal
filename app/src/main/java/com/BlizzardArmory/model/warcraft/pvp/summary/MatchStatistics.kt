package com.BlizzardArmory.model.warcraft.pvp.summary

import com.google.gson.annotations.SerializedName


data class MatchStatistics(

        @SerializedName("played") val played: Int,
        @SerializedName("won") val won: Int,
        @SerializedName("lost") val lost: Int
)
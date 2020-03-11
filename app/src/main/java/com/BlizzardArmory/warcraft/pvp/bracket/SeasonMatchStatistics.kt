package com.BlizzardArmory.warcraft.pvp.bracket

import com.google.gson.annotations.SerializedName

data class SeasonMatchStatistics(

        @SerializedName("played") val played: Int,
        @SerializedName("won") val won: Int,
        @SerializedName("lost") val lost: Int
)
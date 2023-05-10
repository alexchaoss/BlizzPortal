package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SeasonMatchStatistics(

    @SerializedName("played") val played: Int,
    @SerializedName("won") val won: Int,
    @SerializedName("lost") val lost: Int
)
package com.BlizzardArmory.model.warcraft.pvp.summary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class PvpMapStatistics(

    @SerializedName("world_map") val world_map: WorldMap,
    @SerializedName("match_statistics") val match_statistics: MatchStatistics
)
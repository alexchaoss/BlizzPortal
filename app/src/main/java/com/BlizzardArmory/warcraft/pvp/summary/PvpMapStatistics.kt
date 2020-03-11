package com.BlizzardArmory.warcraft.pvp.summary

import com.google.gson.annotations.SerializedName

data class PvpMapStatistics(

        @SerializedName("world_map") val world_map: WorldMap,
        @SerializedName("match_statistics") val match_statistics: MatchStatistics
)
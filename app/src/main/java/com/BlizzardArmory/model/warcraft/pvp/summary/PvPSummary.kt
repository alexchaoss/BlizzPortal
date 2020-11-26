package com.BlizzardArmory.model.warcraft.pvp.summary

import com.google.gson.annotations.SerializedName


data class PvPSummary(

        @SerializedName("_links") val _links: _links,
        @SerializedName("brackets") val brackets: List<Brackets>,
        @SerializedName("honor_level") val honor_level: Int,
        @SerializedName("pvp_map_statistics") val pvp_map_statistics: List<PvpMapStatistics>,
        @SerializedName("honorable_kills") val honorable_kills: Int,
        @SerializedName("character") val character: Character
)
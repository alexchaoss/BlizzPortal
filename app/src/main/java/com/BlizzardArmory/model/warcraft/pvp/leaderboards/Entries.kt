package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import com.BlizzardArmory.model.warcraft.mythicraid.Faction
import com.google.gson.annotations.SerializedName

data class Entries(

    @SerializedName("character") val character: Character,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("rank") val rank: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("season_match_statistics") val season_matchStatistics: SeasonMatchStatistics,
    @SerializedName("tier") val tier: Tier
)
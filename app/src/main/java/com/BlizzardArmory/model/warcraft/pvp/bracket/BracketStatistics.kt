package com.BlizzardArmory.model.warcraft.pvp.bracket

import com.google.gson.annotations.SerializedName


data class BracketStatistics(

        @SerializedName("_links") val _links: _links,
        @SerializedName("character") val character: Character,
        @SerializedName("faction") val faction: Faction,
        @SerializedName("bracket") val bracket: Bracket,
        @SerializedName("rating") val rating: Int,
        @SerializedName("season") val season: Season,
        @SerializedName("tier") val tier: Tier,
        @SerializedName("season_match_statistics") val season_matchStatistics: SeasonMatchStatistics,
        @SerializedName("weekly_match_statistics") val weekly_matchStatistics: WeeklyMatchStatistics
)
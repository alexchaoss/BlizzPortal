package com.BlizzardArmory.model.warcraft.pvp.bracket

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.BlizzardArmory.model.warcraft.common.Faction
import com.google.gson.annotations.SerializedName


@Keep
data class BracketStatistics(

    @SerializedName("_links") val links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("bracket") val bracket: Bracket,
    @SerializedName("rating") val rating: Int,
    @SerializedName("season") val season: Season,
    @SerializedName("tier") val tier: Tier,
    @SerializedName("season_match_statistics") val season_matchStatistics: SeasonMatchStatistics,
    @SerializedName("weekly_match_statistics") val weekly_matchStatistics: WeeklyMatchStatistics
)
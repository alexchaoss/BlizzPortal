package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.index

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

data class LeaderboardsIndex(

    @SerializedName("_links") val links: Links,
    @SerializedName("current_leaderboards") val current_leaderboards: List<CurrentLeaderboards>
)
package com.BlizzardArmory.model.warcraft.pvp.leaderboards.season

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

data class SeasonIndex(

    @SerializedName("_links") val _links: Links,
    @SerializedName("seasons") val seasons: List<Seasons>,
    @SerializedName("current_season") val current_season: Current_season
)
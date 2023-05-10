package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

@Keep
data class Leaderboard(

    @SerializedName("_links") val _links: Links,
    @SerializedName("season") val season: Season,
    @SerializedName("name") val name: String,
    @SerializedName("bracket") val bracket: Bracket,
    @SerializedName("entries") val entries: List<Entries>
)
package com.BlizzardArmory.warcraft.reputations.index.faction

import com.google.gson.annotations.SerializedName

data class Faction(

        @SerializedName("_links") val _links: _links,
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("reputation_tiers") val reputation_tiers: Reputation_tiers,
        @SerializedName("factions") val factions: List<Factions>,
        @SerializedName("is_header") val is_header: Boolean
)
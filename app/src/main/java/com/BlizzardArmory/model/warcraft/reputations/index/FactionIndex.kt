package com.BlizzardArmory.model.warcraft.reputations.index

import com.google.gson.annotations.SerializedName


data class FactionIndex(

        @SerializedName("_links") val _links: _links,
        @SerializedName("factions") val factions: List<Factions>,
        @SerializedName("root_factions") val root_factions: List<Root_factions>
)
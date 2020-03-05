package com.BlizzardArmory.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class EncountersInformation(

        @SerializedName("_links") val _links: _links,
        @SerializedName("character") val character: Character,
        @SerializedName("expansions") val expansions: List<Expansions>
)
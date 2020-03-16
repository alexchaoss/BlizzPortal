package com.BlizzardArmory.warcraft.reputations.characterreputions

import com.google.gson.annotations.SerializedName

data class Reputation(

        @SerializedName("_links") val _links: _links,
        @SerializedName("character") val character: Character,
        @SerializedName("reputations") val reputations: List<Reputations>
)
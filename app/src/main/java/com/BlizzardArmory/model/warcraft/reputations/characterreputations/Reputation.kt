package com.BlizzardArmory.model.warcraft.reputations.characterreputations

import com.google.gson.annotations.SerializedName


data class Reputation(

        @SerializedName("_links") val _links: _links,
        @SerializedName("character") val character: Character,
        @SerializedName("reputations") val reputations: List<Reputations>
)
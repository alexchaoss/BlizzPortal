package com.BlizzardArmory.model.starcraft.league

import com.google.gson.annotations.SerializedName


data class League(

        @SerializedName("_links") val _links: _links,
        @SerializedName("key") val key: Key,
        @SerializedName("tier") val tier: List<Tier>
)
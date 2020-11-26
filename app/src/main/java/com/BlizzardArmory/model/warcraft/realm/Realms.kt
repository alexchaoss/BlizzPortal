package com.BlizzardArmory.model.warcraft.realm

import com.google.gson.annotations.SerializedName


data class Realms(

        @SerializedName("_links") val _links: Links,
        @SerializedName("realms") val realms: List<Realm>
)
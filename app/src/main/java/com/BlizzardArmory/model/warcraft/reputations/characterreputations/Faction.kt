package com.BlizzardArmory.model.warcraft.reputations.characterreputations

import com.google.gson.annotations.SerializedName


data class Faction(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Int
)
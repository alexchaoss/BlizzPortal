package com.BlizzardArmory.warcraft.reputations.index.faction

import com.google.gson.annotations.SerializedName

data class Factions(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Int
)
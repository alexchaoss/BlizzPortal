package com.BlizzardArmory.model.warcraft.reputations.index

import com.google.gson.annotations.SerializedName


data class Root_factions(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Int
)
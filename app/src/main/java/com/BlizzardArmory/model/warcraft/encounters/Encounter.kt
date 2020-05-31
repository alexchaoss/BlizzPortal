package com.BlizzardArmory.model.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class Encounter(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Long
)
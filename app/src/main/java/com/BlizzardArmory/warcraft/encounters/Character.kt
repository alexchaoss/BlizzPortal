package com.BlizzardArmory.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class Character(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Long,
        @SerializedName("realm") val realm: Realm
)
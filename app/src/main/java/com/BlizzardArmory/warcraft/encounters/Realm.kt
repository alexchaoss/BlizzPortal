package com.BlizzardArmory.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class Realm(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Long,
        @SerializedName("slug") val slug: String
)
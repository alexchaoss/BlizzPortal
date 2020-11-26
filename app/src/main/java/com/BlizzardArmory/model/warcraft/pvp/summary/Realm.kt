package com.BlizzardArmory.model.warcraft.pvp.summary

import com.google.gson.annotations.SerializedName


data class Realm(

        @SerializedName("key") val key: Key,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Int,
        @SerializedName("slug") val slug: String
)
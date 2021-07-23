package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class Type(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
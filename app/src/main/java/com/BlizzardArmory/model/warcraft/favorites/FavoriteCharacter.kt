package com.BlizzardArmory.model.warcraft.favorites

import com.google.gson.annotations.SerializedName

class FavoriteCharacter {
    @SerializedName("character_name")
    val name: String? = null

    @SerializedName("realm")
    val realm: String? = null

    @SerializedName("region")
    val region: String? = null
}
package com.BlizzardArmory.model.warcraft.favorites

import com.google.gson.annotations.SerializedName

class FavoriteCharacters {
    @SerializedName("characters")
    val characters = listOf<FavoriteCharacter>()
}
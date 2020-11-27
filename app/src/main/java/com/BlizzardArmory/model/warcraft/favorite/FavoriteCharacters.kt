package com.BlizzardArmory.model.warcraft.favorite

import com.google.gson.annotations.SerializedName


class FavoriteCharacters {
    @SerializedName("characters")
    val characters = arrayListOf<FavoriteCharacter>()
}
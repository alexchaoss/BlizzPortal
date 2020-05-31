package com.BlizzardArmory.model.warcraft.favorite

import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.google.gson.annotations.SerializedName

class FavoriteCharacter(characterSummary: CharacterSummary, region: String) {

    @SerializedName("character_summary")
    var characterSummary: CharacterSummary? = null

    @SerializedName("region")
    var region: String? = null

    init {
        this.characterSummary = characterSummary
        this.region = region
    }
}
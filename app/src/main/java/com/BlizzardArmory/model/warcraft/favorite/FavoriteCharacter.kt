package com.BlizzardArmory.model.warcraft.favorite

import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.google.gson.annotations.SerializedName


class FavoriteCharacter(characterSummary: CharacterSummary?, region: String?, classic: Boolean?, classic1x: Boolean?) {

    @SerializedName("character_summary")
    var characterSummary: CharacterSummary? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("classic")
    var classic: Boolean? = null

    @SerializedName("classic1x")
    var classic1x: Boolean? = null

    init {
        this.characterSummary = characterSummary
        this.region = region
        this.classic = classic
        this.classic1x = classic1x
    }
}
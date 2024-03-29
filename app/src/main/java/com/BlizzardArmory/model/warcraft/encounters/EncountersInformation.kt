package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.google.gson.annotations.SerializedName


@Keep
data class EncountersInformation(

    @SerializedName("_links") val links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("expansions") val expansions: List<Expansions>?
)
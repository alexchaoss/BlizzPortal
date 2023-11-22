package com.BlizzardArmory.model.warcraft.reputations.characterreputations

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.google.gson.annotations.SerializedName


@Keep
data class Reputation(

    @SerializedName("_links") val links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("reputations") val reputations: List<Reputations>
)
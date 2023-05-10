package com.BlizzardArmory.model.warcraft.reputations.characterreputations

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.reputations.Faction
import com.google.gson.annotations.SerializedName


@Keep
data class Reputations(

    @SerializedName("faction") val faction: Faction,
    @SerializedName("standing") val standing: Standing
)
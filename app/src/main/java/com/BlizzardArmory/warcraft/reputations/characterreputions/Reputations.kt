package com.BlizzardArmory.warcraft.reputations.characterreputions

import com.google.gson.annotations.SerializedName

data class Reputations(

        @SerializedName("faction") val faction: Faction,
        @SerializedName("standing") val standing: Standing
)
package com.BlizzardArmory.warcraft.reputations.characterreputions

import com.google.gson.annotations.SerializedName

data class Standing(

        @SerializedName("raw") val raw: Int,
        @SerializedName("value") val value: Int,
        @SerializedName("max") val max: Int,
        @SerializedName("tier") val tier: Int,
        @SerializedName("name") val name: String
)
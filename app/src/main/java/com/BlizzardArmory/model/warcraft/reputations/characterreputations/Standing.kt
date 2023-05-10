package com.BlizzardArmory.model.warcraft.reputations.characterreputations

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Standing(

    @SerializedName("raw") val raw: Int,
    @SerializedName("value") val value: Int,
    @SerializedName("max") val max: Int,
    @SerializedName("tier") val tier: Int,
    @SerializedName("name") val name: String
)
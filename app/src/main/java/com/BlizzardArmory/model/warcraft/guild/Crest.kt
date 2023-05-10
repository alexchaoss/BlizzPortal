package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Crest(

    @SerializedName("emblem") val emblem: Emblem,
    @SerializedName("border") val border: Border,
    @SerializedName("background") val background: Background
)
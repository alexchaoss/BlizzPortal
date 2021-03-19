package com.BlizzardArmory.model.warcraft.guild

import com.google.gson.annotations.SerializedName


data class Crest(

    @SerializedName("emblem") val emblem: Emblem,
    @SerializedName("border") val border: Border,
    @SerializedName("background") val background: Background
)
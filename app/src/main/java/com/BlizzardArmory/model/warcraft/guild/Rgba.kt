package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Rgba(

    @SerializedName("r") val r: Int,
    @SerializedName("g") val g: Int,
    @SerializedName("b") val b: Int,
    @SerializedName("a") val a: Int
)
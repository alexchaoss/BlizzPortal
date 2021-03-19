package com.BlizzardArmory.model.warcraft.guild

import com.google.gson.annotations.SerializedName


data class Rgba(

    @SerializedName("r") val r: Int,
    @SerializedName("g") val g: Int,
    @SerializedName("b") val b: Int,
    @SerializedName("a") val a: Int
)
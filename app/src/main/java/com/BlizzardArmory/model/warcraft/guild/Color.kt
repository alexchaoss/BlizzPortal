package com.BlizzardArmory.model.warcraft.guild

import com.google.gson.annotations.SerializedName


data class Color(

    @SerializedName("id") val id: Int,
    @SerializedName("rgba") val rgba: Rgba
)
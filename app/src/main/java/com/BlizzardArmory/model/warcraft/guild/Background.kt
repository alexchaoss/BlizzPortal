package com.BlizzardArmory.model.warcraft.guild

import com.google.gson.annotations.SerializedName


data class Background(

    @SerializedName("color") val color: Color
)
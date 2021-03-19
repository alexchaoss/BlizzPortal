package com.BlizzardArmory.model.warcraft.guild

import com.BlizzardArmory.model.common.Media
import com.google.gson.annotations.SerializedName


data class Border(

    @SerializedName("id") val id: Int,
    @SerializedName("media") val media: Media,
    @SerializedName("color") val color: Color
)
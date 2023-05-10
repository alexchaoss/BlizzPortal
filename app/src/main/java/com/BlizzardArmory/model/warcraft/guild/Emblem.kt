package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Media
import com.google.gson.annotations.SerializedName


@Keep
data class Emblem(

    @SerializedName("id") val id: Int,
    @SerializedName("media") val media: Media,
    @SerializedName("color") val color: Color
)
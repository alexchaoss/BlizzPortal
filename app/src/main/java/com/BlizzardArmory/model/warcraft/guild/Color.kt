package com.BlizzardArmory.model.warcraft.guild

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Color(

    @SerializedName("id") val id: Int,
    @SerializedName("rgba") val rgba: Rgba
)
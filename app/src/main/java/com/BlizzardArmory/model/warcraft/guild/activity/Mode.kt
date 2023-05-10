package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Mode(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
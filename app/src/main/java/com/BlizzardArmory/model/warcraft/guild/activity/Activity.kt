package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Activity(

    @SerializedName("type") val type: String
)
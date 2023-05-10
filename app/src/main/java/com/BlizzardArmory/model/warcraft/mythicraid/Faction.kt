package com.BlizzardArmory.model.warcraft.mythicraid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Faction(

    @SerializedName("type") val type: String
)
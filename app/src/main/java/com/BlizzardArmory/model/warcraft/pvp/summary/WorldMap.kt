package com.BlizzardArmory.model.warcraft.pvp.summary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class WorldMap(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
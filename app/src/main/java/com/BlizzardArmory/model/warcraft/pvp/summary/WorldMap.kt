package com.BlizzardArmory.model.warcraft.pvp.summary

import com.google.gson.annotations.SerializedName

data class WorldMap(

        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Int
)
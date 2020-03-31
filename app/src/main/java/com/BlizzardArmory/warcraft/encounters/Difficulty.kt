package com.BlizzardArmory.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class Difficulty(

        @SerializedName("type") val type: String,
        @SerializedName("name") val name: String
)
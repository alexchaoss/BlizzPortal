package com.BlizzardArmory.model.warcraft.encounters

import com.google.gson.annotations.SerializedName

data class Status(

        @SerializedName("type") val type: String,
        @SerializedName("name") val name: String
)
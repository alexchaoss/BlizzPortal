package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import com.google.gson.annotations.SerializedName

data class Seasons(

    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String
)
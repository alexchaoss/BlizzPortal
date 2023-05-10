package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Seasons(

    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String
)
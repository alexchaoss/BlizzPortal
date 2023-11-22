package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SeasonDate(
    @SerializedName("us") val us: Date? = null,
    @SerializedName("eu") val eu: Date? = null,
    @SerializedName("tw") val tw: Date? = null,
    @SerializedName("kr") val kr: Date? = null,
    @SerializedName("cn") val cn: Date? = null
)

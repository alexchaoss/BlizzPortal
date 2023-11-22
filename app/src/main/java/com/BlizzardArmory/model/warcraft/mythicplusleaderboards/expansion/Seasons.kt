package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Seasons(

    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("starts") val starts: SeasonDate,
    @SerializedName("ends") val ends: SeasonDate,
    @SerializedName("dungeons") val dungeons: ArrayList<Dungeons>
)
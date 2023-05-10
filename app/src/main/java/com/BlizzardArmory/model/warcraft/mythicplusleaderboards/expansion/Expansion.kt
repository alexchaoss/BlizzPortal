package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Expansion(

    @SerializedName("seasons") val seasons: List<Seasons>,
    @SerializedName("dungeons") val dungeons: List<Dungeons>
)
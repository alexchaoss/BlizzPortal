package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import com.google.gson.annotations.SerializedName

data class Expansion(

    @SerializedName("seasons") val seasons: List<Seasons>,
    @SerializedName("dungeons") val dungeons: List<Dungeons>
)
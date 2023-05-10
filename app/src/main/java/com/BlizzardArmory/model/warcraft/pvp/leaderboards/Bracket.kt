package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Bracket(

    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String
)
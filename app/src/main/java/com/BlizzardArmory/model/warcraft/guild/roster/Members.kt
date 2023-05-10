package com.BlizzardArmory.model.warcraft.guild.roster

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Members(

    @SerializedName("character") val character: Character,
    @SerializedName("rank") val rank: Int
)
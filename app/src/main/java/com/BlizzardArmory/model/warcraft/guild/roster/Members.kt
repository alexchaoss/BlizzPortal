package com.BlizzardArmory.model.warcraft.guild.roster

import com.google.gson.annotations.SerializedName


data class Members(

    @SerializedName("character") val character: Character,
    @SerializedName("rank") val rank: Int
)
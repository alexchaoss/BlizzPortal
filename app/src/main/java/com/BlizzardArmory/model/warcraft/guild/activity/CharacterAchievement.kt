package com.BlizzardArmory.model.warcraft.guild.activity

import com.google.gson.annotations.SerializedName


data class CharacterAchievement(

    @SerializedName("character") val character: Character,
    @SerializedName("achievement") val achievement: Achievement
)
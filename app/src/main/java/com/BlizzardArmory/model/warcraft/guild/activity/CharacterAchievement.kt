package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class CharacterAchievement(

    @SerializedName("character") val character: Character,
    @SerializedName("achievement") val achievement: Achievement
)
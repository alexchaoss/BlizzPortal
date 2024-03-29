package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Activities(

    @SerializedName("character_achievement") val characterAchievement: CharacterAchievement,
    @SerializedName("encounter_completed") val encounterCompleted: EncounterCompleted,
    @SerializedName("activity") val activity: Activity,
    @SerializedName("timestamp") val timestamp: Long
)
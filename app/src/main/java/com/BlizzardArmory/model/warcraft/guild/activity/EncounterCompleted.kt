package com.BlizzardArmory.model.warcraft.guild.activity

import com.google.gson.annotations.SerializedName


data class EncounterCompleted(

    @SerializedName("encounter") val encounter: Encounter,
    @SerializedName("mode") val mode: Mode
)
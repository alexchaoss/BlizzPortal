package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class EncounterCompleted(

    @SerializedName("encounter") val encounter: Encounter,
    @SerializedName("mode") val mode: Mode
)
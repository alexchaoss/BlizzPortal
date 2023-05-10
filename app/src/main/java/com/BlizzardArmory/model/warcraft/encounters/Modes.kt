package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Modes(

    @SerializedName("difficulty") val difficulty: Difficulty,
    @SerializedName("status") val status: Status,
    @SerializedName("progress") val progress: Progress
)
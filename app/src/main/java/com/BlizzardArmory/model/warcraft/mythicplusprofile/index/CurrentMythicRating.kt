package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.mythicplusprofile.Color
import com.google.gson.annotations.SerializedName


@Keep
data class CurrentMythicRating(
    @SerializedName("color") var color: Color,
    @SerializedName("rating") var rating: Double
)
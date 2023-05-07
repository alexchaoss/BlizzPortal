package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import com.BlizzardArmory.model.warcraft.mythicplusprofile.Color
import com.google.gson.annotations.SerializedName


data class CurrentMythicRating(
    @SerializedName("color") var color: Color,
    @SerializedName("rating") var rating: Double
)
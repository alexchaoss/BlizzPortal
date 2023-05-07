package com.BlizzardArmory.model.warcraft.mythicplusprofile

import com.google.gson.annotations.SerializedName


data class MythicRating(

    @SerializedName("color") var color: Color,
    @SerializedName("rating") var rating: Double

)
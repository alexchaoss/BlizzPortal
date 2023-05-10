package com.BlizzardArmory.model.warcraft.mythicplusprofile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class MapRating(

    @SerializedName("color") var color: Color,
    @SerializedName("rating") var rating: Double

)
package com.BlizzardArmory.model.warcraft.statistic

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Mastery.
 */
@Keep
data class Mastery(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float,

    @SerializedName("value")
    var value: Float
)
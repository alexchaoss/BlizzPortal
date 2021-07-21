package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Mastery.
 */
data class Mastery(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float,

    @SerializedName("value")
    var value: Float
)
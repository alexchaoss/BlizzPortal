package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Avoidance.
 */
data class Avoidance(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float

)
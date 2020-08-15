package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Avoidance.
 */
data class Avoidance(

        @SerializedName("rating")
        @Expose
        var rating: Int,

        @SerializedName("rating_bonus")
        @Expose
        var ratingBonus: Float

)
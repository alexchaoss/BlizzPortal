package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Lifesteal.
 */
data class Lifesteal(

        @SerializedName("rating")
        @Expose
        var rating: Int,

        @SerializedName("rating_bonus")
        @Expose
        var ratingBonus: Float,

        @SerializedName("value")
        @Expose
        var value: Float
)
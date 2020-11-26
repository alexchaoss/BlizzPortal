package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Ranged haste.
 */
data class RangedHaste(

        @SerializedName("rating")
        var rating: Int,

        @SerializedName("rating_bonus")
        var ratingBonus: Float,

        @SerializedName("value")
        var value: Float
)
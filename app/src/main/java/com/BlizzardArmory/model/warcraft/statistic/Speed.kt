package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Speed.
 */
class Speed(

        @SerializedName("rating")
        var rating: Int,

        @SerializedName("rating_bonus")
        var ratingBonus: Float

)
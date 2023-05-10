package com.BlizzardArmory.model.warcraft.statistic

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Melee crit.
 */
@Keep
data class MeleeCrit(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float,

    @SerializedName("value")
    var value: Float
)
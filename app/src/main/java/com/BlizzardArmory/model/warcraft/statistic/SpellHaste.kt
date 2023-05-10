package com.BlizzardArmory.model.warcraft.statistic

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Spell haste.
 */
@Keep
data class SpellHaste(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float,

    @SerializedName("value")
    var value: Float
)
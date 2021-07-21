package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Spell haste.
 */
data class SpellHaste(

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("rating_bonus")
    var ratingBonus: Float,

    @SerializedName("value")
    var value: Float
)
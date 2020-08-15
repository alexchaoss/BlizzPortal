package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Spell crit.
 */
data class SpellCrit(

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
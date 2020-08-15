package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Spell tooltip.
 */
data class SpellTooltip(

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("cast_time")
        @Expose
        var castTime: String,

        @SerializedName("cooldown")
        @Expose
        var cooldown: String,

        @SerializedName("power_cost")
        @Expose
        var powerCost: String,

        @SerializedName("range")
        @Expose
        var range: String
)
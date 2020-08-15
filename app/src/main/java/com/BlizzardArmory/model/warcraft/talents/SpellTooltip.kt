package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Spell tooltip.
 */
data class SpellTooltip(

        @SerializedName("spell")
        @Expose
        var spell: Spell,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("cast_time")
        @Expose
        var castTime: String,

        @SerializedName("power_cost")
        @Expose
        var powerCost: String,

        @SerializedName("range")
        @Expose
        var range: String,

        @SerializedName("cooldown")
        @Expose
        var cooldown: String

)
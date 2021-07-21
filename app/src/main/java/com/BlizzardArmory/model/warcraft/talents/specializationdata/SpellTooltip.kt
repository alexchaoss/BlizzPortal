package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Spell tooltip.
 */
data class SpellTooltip(

    @SerializedName("description")
    var description: String,

    @SerializedName("cast_time")
    var castTime: String,

    @SerializedName("cooldown")
    var cooldown: String,

    @SerializedName("power_cost")
    var powerCost: String,

    @SerializedName("range")
    var range: String
)
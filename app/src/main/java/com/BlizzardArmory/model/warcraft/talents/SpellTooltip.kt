package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName


/**
 * The type Spell tooltip.
 */
data class SpellTooltip(

    @SerializedName("spell")
    var spell: Spell,

    @SerializedName("description")
    var description: String,

    @SerializedName("cast_time")
    var castTime: String,

    @SerializedName("power_cost")
    var powerCost: String,

    @SerializedName("range")
    var range: String,

    @SerializedName("cooldown")
    var cooldown: String

)
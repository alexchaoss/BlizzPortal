package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Main spell tooltip.
 */
data class MainSpellTooltip(

    @SerializedName("spell")
    var spell: Spell,

    @SerializedName("description")
    var description: String,

    @SerializedName("cast_time")
    var castTime: String,

    @SerializedName("range")
    var range: String

)
package com.BlizzardArmory.model.warcraft.equipment

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
        var castTime: String

)
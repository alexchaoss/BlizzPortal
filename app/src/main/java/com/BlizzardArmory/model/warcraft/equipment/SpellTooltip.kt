package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Spell tooltip.
 */
@Keep
data class SpellTooltip(

    @SerializedName("spell")
    var spell: Spell,

    @SerializedName("description")
    var description: String,

    @SerializedName("cast_time")
    var castTime: String

)
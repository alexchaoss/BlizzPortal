package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Main spell tooltip.
 */
@Keep
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
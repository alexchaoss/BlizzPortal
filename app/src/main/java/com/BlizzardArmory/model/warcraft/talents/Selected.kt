package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Selected.
 */
data class Selected(

        @SerializedName("talent")
        @Expose
        var talent: Talent,

        @SerializedName("spelltooltip")
        @Expose
        var spellTooltip: SpellTooltip

)
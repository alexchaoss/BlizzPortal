package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName


/**
 * The type Selected.
 */
data class Selected(

        @SerializedName("talent")
        var talent: Talent,

        @SerializedName("spelltooltip")
        var spellTooltip: SpellTooltip

)
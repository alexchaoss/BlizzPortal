package com.BlizzardArmory.model.warcraft.talentold

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
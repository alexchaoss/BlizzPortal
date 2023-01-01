package com.BlizzardArmory.model.warcraft.talentold

import com.google.gson.annotations.SerializedName


/**
 * The type Talent.
 */
@Deprecated("Don't use this anymore")
data class Talent(

    @SerializedName("talent")
    var talent: TalentName,

    @SerializedName("spell_tooltip")
    var spellTooltip: SpellTooltip

)
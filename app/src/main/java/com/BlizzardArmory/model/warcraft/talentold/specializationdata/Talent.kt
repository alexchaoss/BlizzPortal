package com.BlizzardArmory.model.warcraft.talentold.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Talent.
 */
data class Talent(

    @SerializedName("talent")
    var talent: TalentInfo,

    @SerializedName("spell_tooltip")
    var spellTooltip: SpellTooltip
)
package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName


/**
 * The type Talent.
 */
data class Talent(

    @SerializedName("talent")
    var talent: TalentName,

    @SerializedName("spell_tooltip")
    var spellTooltip: SpellTooltip

)
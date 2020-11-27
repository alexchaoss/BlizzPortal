package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Pvp talent.
 */
data class PvpTalent(

        @SerializedName("talent")
        var talent: Talent,

        @SerializedName("spell_tooltip")
        var spellTooltip: SpellTooltip

)
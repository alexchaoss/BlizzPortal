package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Pvp talent.
 */
data class PvpTalent(

        @SerializedName("talent")
        @Expose
        var talent: Talent,

        @SerializedName("spell_tooltip")
        @Expose
        var spellTooltip: SpellTooltip

)
package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Talent.
 */
data class Talent(

        @SerializedName("talent")
        @Expose
        var talent: TalentInfo,

        @SerializedName("spell_tooltip")
        @Expose
        var spellTooltip: SpellTooltip
)
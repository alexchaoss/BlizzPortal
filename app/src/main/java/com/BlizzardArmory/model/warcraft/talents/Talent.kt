package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Talent.
 */
data class Talent(

        @SerializedName("talent")
        @Expose
        var talent: TalentName,

        @SerializedName("spell_tooltip")
        @Expose
        var spellTooltip: SpellTooltip

)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Selected power.
 */
data class SelectedPower(

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("tier")
        @Expose
        var tier: Int,

        @SerializedName("spell_tooltip")
        @Expose
        var spellTooltip: SpellTooltip,

        @SerializedName("is_display_hidden")
        @Expose
        var isIsDisplayHidden: Boolean

)
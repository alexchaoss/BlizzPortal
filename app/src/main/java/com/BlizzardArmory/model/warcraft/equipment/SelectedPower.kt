package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Selected power.
 */
data class SelectedPower(

        @SerializedName("id")
        var id: Long,

        @SerializedName("tier")
        var tier: Int,

        @SerializedName("spell_tooltip")
        var spellTooltip: SpellTooltip,

        @SerializedName("is_display_hidden")
        var isIsDisplayHidden: Boolean

)
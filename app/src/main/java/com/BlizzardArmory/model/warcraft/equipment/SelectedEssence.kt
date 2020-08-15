package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Selected essence.
 */
data class SelectedEssence(

        @SerializedName("slot")
        @Expose
        var slot: Int,

        @SerializedName("rank")
        @Expose
        var rank: Int,

        @SerializedName("main_spell_tooltip")
        @Expose
        var mainSpellTooltip: MainSpellTooltip,

        @SerializedName("passive_spell_tooltip")
        @Expose
        var passiveSpellTooltip: PassiveSpellTooltip,

        @SerializedName("essence")
        @Expose
        var essence: Essence,

        @SerializedName("media")
        @Expose
        var media: Media

)
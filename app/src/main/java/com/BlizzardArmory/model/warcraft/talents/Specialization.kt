package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.SerializedName


/**
 * The type Specialization.
 */
data class Specialization(
        @SerializedName("specialization")
        var specialization: SpecializationName,

        @SerializedName("talents")
        var talents: List<Talent>,

        @SerializedName("glyphs")
        var glyphs: List<Glyph>,

        @SerializedName("pvp_talent_slots")
        var pvpTalentSlots: List<PvpTalentSlot>

)
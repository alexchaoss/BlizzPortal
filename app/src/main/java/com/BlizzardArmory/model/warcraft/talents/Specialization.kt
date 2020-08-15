package com.BlizzardArmory.model.warcraft.talents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Specialization.
 */
data class Specialization(
        @SerializedName("specialization")
        @Expose
        var specialization: SpecializationName,

        @SerializedName("talents")
        @Expose
        var talents: List<Talent>,

        @SerializedName("glyphs")
        @Expose
        var glyphs: List<Glyph>,

        @SerializedName("pvp_talent_slots")
        @Expose
        var pvpTalentSlots: List<PvpTalentSlot>

)
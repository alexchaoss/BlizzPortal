package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class SpecializationGroups(
    @SerializedName("is_active") var isActive: Boolean,
    @SerializedName("specializations") var specializations: ArrayList<ClassicSpecializations>,
    @SerializedName("glyphs") var glyphs: ArrayList<Glyphs>
)
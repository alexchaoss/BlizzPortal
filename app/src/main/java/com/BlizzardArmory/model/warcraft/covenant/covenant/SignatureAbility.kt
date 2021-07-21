package com.BlizzardArmory.model.warcraft.covenant.covenant

import com.google.gson.annotations.SerializedName


data class SignatureAbility(

    @SerializedName("id") val id: Int,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip
)
package com.BlizzardArmory.model.warcraft.covenant.conduit

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


@Keep
data class Ranks(

    @SerializedName("id") val id: Int,
    @SerializedName("tier") val tier: Int,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip
)
package com.BlizzardArmory.model.warcraft.covenant.covenant

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


@Keep
data class SignatureAbility(

    @SerializedName("id") val id: Int,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip
)
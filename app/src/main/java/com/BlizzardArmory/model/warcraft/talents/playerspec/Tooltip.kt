package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.SpellTooltip
import com.google.gson.annotations.SerializedName


@Keep
data class Tooltip (

	@SerializedName("talent") val talent : Talent,
	@SerializedName("spell_tooltip") val spellTooltip : SpellTooltip
)
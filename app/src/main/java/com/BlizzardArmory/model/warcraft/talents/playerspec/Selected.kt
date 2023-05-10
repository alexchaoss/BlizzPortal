package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Selected (

	@SerializedName("talent") val talent : Talent,
	@SerializedName("spell_tooltip") val spellTooltip : SpellTooltip
)
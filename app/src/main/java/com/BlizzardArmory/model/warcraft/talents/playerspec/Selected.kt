package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class Selected (

	@SerializedName("talent") val talent : Talent,
	@SerializedName("spell_tooltip") val spellTooltip : SpellTooltip
)
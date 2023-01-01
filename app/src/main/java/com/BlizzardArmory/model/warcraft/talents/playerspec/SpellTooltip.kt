package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class SpellTooltip (

	@SerializedName("spell") val spell: Spell,
	@SerializedName("description") val description: String,
	@SerializedName("cast_time") val castTime: String,
	@SerializedName("power_cost") val powerCost: String?,
	@SerializedName("range") val range: String,
	@SerializedName("cooldown") val cooldown: String
)
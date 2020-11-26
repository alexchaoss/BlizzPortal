package com.BlizzardArmory.model.warcraft.covenant.conduit

import com.google.gson.annotations.SerializedName


data class Ranks (

	@SerializedName("id") val id : Int,
	@SerializedName("tier") val tier : Int,
	@SerializedName("spell_tooltip") val spell_tooltip : SpellTooltip
)
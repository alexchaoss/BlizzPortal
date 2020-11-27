package com.BlizzardArmory.model.warcraft.covenant.covenant

import com.google.gson.annotations.SerializedName


data class SpellTooltip (

	@SerializedName("spell") val spell : Spell,
	@SerializedName("description") val description : String,
	@SerializedName("cast_time") val cast_time : String,
	@SerializedName("range") val range : String,
	@SerializedName("cooldown") val cooldown : String
)
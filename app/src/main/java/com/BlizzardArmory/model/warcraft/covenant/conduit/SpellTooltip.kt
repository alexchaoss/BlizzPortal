package com.BlizzardArmory.model.warcraft.covenant.conduit

import com.google.gson.annotations.SerializedName


data class SpellTooltip (

	@SerializedName("spell") val spell : Spell,
	@SerializedName("description") val description : String,
	@SerializedName("cast_time") val cast_time : String
)
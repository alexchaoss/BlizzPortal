package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SpellTooltip (

	@SerializedName("spell") val spell : Spell,
	@SerializedName("description") val description : String,
	@SerializedName("cast_time") val cast_time : String,
	@SerializedName("power_cost") val power_cost : String,
	@SerializedName("range") val range : String
)
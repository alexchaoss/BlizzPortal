package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Tooltip (

	@SerializedName("talent") val talent : Talent,
	@SerializedName("spell_tooltip") val spell_tooltip : SpellTooltip
)
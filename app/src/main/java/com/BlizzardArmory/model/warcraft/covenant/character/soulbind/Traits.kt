package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class Traits (

	@SerializedName("display_order") val display_order : Int,
	@SerializedName("tier") val tier : Int,
	@SerializedName("trait") val trait : Trait
)
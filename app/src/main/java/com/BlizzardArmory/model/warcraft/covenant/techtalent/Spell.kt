package com.BlizzardArmory.model.warcraft.covenant.techtalent

import com.google.gson.annotations.SerializedName


data class Spell (

	@SerializedName("key") val key : Key,
	@SerializedName("name") val name : String,
	@SerializedName("id") val id : Int
)
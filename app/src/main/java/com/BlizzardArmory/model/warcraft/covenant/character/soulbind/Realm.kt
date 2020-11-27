package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class Realm (

	@SerializedName("id") val id : Int,
	@SerializedName("key") val key : Key,
	@SerializedName("name") val name : String,
	@SerializedName("slug") val slug : String
)
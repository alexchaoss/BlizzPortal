package com.BlizzardArmory.model.warcraft.covenant.soulbind

import com.google.gson.annotations.SerializedName


data class Follower (

	@SerializedName("name") val name : String,
	@SerializedName("id") val id : Int
)
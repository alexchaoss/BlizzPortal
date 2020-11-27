package com.BlizzardArmory.model.warcraft.covenant.conduit

import com.google.gson.annotations.SerializedName


data class SocketType (

	@SerializedName("type") val type : String,
	@SerializedName("name") val name : String
)
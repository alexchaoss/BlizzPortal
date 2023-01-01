package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Talent (

	@SerializedName("key") val key : Key,
	@SerializedName("name") val name : String,
	@SerializedName("id") val id : Int
)
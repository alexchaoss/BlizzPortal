package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class Seasons (

	@SerializedName("key") val key : Key,
	@SerializedName("id") val id : Int
)
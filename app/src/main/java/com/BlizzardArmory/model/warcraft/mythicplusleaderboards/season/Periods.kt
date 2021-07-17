package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class Periods (

	@SerializedName("key") val key : Key,
	@SerializedName("id") val id : Int
)
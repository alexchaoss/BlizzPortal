package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class Specialization (

	@SerializedName("key") val key : Key,
	@SerializedName("id") val id : Int
)
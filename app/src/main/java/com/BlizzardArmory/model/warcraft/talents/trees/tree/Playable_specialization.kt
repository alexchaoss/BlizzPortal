package com.BlizzardArmory.model.warcraft.talents.trees.tree

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Playable_specialization (

	@SerializedName("key") val key : Key,
	@SerializedName("name") val name : String,
	@SerializedName("id") val id : Int
)
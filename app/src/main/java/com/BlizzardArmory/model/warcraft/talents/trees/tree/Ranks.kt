package com.BlizzardArmory.model.warcraft.talents.trees.tree
import com.google.gson.annotations.SerializedName


data class Ranks (

	@SerializedName("rank") val rank : Int,
	@SerializedName("tooltip") val tooltip : Tooltip
)
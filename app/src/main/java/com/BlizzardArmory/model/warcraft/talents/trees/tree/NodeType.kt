package com.BlizzardArmory.model.warcraft.talents.trees.tree
import com.google.gson.annotations.SerializedName


data class NodeType (

	@SerializedName("id") val id : Int,
	@SerializedName("type") val type : String
)
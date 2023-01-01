package com.BlizzardArmory.model.warcraft.talents.trees.tree
import com.google.gson.annotations.SerializedName


data class ClassTalentNodes (

	@SerializedName("id") val id : Int,
	@SerializedName("unlocks") val unlocks : List<Int>,
	@SerializedName("node_type") val node_type : NodeType,
	@SerializedName("ranks") val ranks : List<Ranks>,
	@SerializedName("display_row") val display_row : Int,
	@SerializedName("display_col") val display_col : Int,
	@SerializedName("raw_position_x") val raw_position_x : Int,
	@SerializedName("raw_position_y") val raw_position_y : Int
)
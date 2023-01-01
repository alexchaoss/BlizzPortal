package com.BlizzardArmory.model.warcraft.talents.trees.tree
import com.google.gson.annotations.SerializedName


data class RestrictionLines (

	@SerializedName("required_points") val required_points : Int,
	@SerializedName("restricted_row") val restricted_row : Double,
	@SerializedName("is_for_class") val is_for_class : Boolean
)
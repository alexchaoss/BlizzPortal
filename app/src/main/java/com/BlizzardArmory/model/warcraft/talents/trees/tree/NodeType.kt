package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class NodeType (

	@SerializedName("id") val id : Int,
	@SerializedName("type") val type : String
)
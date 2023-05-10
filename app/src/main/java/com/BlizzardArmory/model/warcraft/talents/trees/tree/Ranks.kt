package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Ranks (

	@SerializedName("rank") val rank : Int,
	@SerializedName("tooltip") val tooltip : Tooltip
)
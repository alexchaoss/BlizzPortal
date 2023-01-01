package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class SelectedClassTalents (

	@SerializedName("id") val id : Int,
	@SerializedName("rank") val rank : Int,
	@SerializedName("tooltip") val tooltip : Tooltip
)
package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SelectedSpecTalents (

	@SerializedName("id") val id : Int,
	@SerializedName("rank") val rank : Int,
	@SerializedName("tooltip") val tooltip : Tooltip
)
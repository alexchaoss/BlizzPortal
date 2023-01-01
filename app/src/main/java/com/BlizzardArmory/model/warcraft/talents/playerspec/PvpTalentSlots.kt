package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.google.gson.annotations.SerializedName


data class PvpTalentSlots (

	@SerializedName("selected") val selected : Selected,
	@SerializedName("slot_number") val slot_number : Int
)
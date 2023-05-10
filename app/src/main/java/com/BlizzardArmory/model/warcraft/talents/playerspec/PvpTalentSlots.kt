package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class PvpTalentSlots (

	@SerializedName("selected") val selected : Selected,
	@SerializedName("slot_number") val slot_number : Int
)
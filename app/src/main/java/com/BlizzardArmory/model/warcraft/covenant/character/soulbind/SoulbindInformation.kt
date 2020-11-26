package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class SoulbindInformation (

		@SerializedName("_links") val _links : _links,
		@SerializedName("character") val character : Character,
		@SerializedName("chosen_covenant") val chosenCovenant : ChosenCovenant,
		@SerializedName("renown_level") val renownLevel : Int,
		@SerializedName("soulbinds") val soulbinds : List<Soulbinds>
)
package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class Soulbinds (

	@SerializedName("soulbind") val soulbind : Soulbind,
	@SerializedName("traits") val traits : List<Traits>,
	@SerializedName("is_active") val active: Boolean
)
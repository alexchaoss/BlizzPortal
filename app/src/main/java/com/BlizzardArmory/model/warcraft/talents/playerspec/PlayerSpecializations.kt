package com.BlizzardArmory.model.warcraft.talents.playerspec

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class PlayerSpecializations (
	@SerializedName("_links") val _links : Links,
	@SerializedName("specializations") val specializations : List<Specializations>,
	@SerializedName("active_specialization") val active_specialization : ActiveSpecialization,
	@SerializedName("character") val character : Character
)
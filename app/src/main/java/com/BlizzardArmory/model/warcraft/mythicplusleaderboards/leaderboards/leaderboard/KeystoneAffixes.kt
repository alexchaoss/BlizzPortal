package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import com.google.gson.annotations.SerializedName

data class KeystoneAffixes (

	@SerializedName("keystone_affix") val keystone_affixstone_affix : KeystoneAffix,
	@SerializedName("starting_level") val starting_level : Int
)
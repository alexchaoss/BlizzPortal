package com.BlizzardArmory.model.warcraft.covenant.techtalenttree

import com.google.gson.annotations.SerializedName


data class TechTalentTree (

	@SerializedName("_links") val _links : _links,
	@SerializedName("id") val id : Int,
	@SerializedName("max_tiers") val max_tiers : Int,
	@SerializedName("talents") val talents : List<Talents>
)
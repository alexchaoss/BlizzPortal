package com.BlizzardArmory.model.warcraft.covenant.soulbind

import com.google.gson.annotations.SerializedName


data class Soulbind (

	@SerializedName("_links") val _links : _links,
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("covenant") val covenant : Covenant,
	@SerializedName("creature") val creature : Creature,
	@SerializedName("follower") val follower : Follower,
	@SerializedName("talent_tree") val techTalent_tree : TechTalentTree
)
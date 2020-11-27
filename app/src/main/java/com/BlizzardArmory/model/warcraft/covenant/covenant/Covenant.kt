package com.BlizzardArmory.model.warcraft.covenant.covenant

import com.google.gson.annotations.SerializedName


data class Covenant (

		@SerializedName("_links") val _links : _links,
		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String,
		@SerializedName("description") val description : String,
		@SerializedName("signature_ability") val signature_ability : SignatureAbility,
		@SerializedName("class_abilities") val class_abilities : List<ClassAbilities>,
		@SerializedName("soulbinds") val soulbinds : List<Soulbinds>,
		@SerializedName("renown_rewards") val renown_rewards : List<RenownRewards>,
		@SerializedName("media") val media : Media
)
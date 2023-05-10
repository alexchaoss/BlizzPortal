package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName


@Keep
data class Character (

	@SerializedName("key") val key : Key,
	@SerializedName("name") val name : String,
	@SerializedName("id") val id : Int,
	@SerializedName("realm") val realm : Realm
)
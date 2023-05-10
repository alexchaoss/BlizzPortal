package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Media (

	@SerializedName("key") val key : Key
)
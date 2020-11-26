package com.BlizzardArmory.model.diablo.data.common

import com.BlizzardArmory.model.diablo.data.common.Player
import com.google.gson.annotations.SerializedName


data class Row (

		@SerializedName("player") val player : List<Player>,
		@SerializedName("order") val order : Int,
		@SerializedName("data") val data : List<Data>
)
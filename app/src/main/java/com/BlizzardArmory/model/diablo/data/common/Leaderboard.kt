package com.BlizzardArmory.model.diablo.data.common

import com.BlizzardArmory.model.diablo.data.common.Column
import com.BlizzardArmory.model.diablo.data.common.Row
import com.BlizzardArmory.model.diablo.data.common._links
import com.google.gson.annotations.SerializedName


data class Leaderboard (

		@SerializedName("_links") val _links : _links,
		@SerializedName("row") val row : List<Row>,
		@SerializedName("key") val key : String,
		@SerializedName("title") val title : String,
		@SerializedName("column") val column : List<Column>,
		@SerializedName("last_update_time") val last_update_time : String,
		@SerializedName("generated_by") val generated_by : String,
		@SerializedName("greater_rift") val greater_rift : Boolean,
		@SerializedName("greater_rift_solo_class") val greater_rift_solo_class : String,
		@SerializedName("era") val era : Int
)
package com.BlizzardArmory.model.diablo.data.eras.index

import com.BlizzardArmory.model.diablo.data.common._links
import com.google.gson.annotations.SerializedName


data class EraIndex (

		@SerializedName("_links") val _links : _links,
		@SerializedName("era") val era : List<Era>,
		@SerializedName("current_era") val current_era : Int,
		@SerializedName("last_update_time") val last_update_time : String,
		@SerializedName("generated_by") val generated_by : String
)
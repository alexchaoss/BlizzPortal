package com.BlizzardArmory.model.diablo.data.eras.era

import com.BlizzardArmory.model.diablo.data.common.LeaderboardInfo
import com.BlizzardArmory.model.diablo.data.common._links
import com.google.gson.annotations.SerializedName


data class Era (

		@SerializedName("_links") val _links : _links,
		@SerializedName("leaderboard") val leaderboardInfo : List<LeaderboardInfo>,
		@SerializedName("era_id") val era_id : Int,
		@SerializedName("era_start_date") val era_start_date : Int,
		@SerializedName("last_update_time") val last_update_time : String,
		@SerializedName("generated_by") val generated_by : String
)
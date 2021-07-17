package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

data class SeasonsIndex (
	@SerializedName("_links") val links : Links,
	@SerializedName("seasons") val seasons : List<Seasons>,
	@SerializedName("current_season") val current_season : Current_season
)
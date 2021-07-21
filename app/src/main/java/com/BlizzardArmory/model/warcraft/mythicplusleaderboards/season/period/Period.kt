package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.period

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName

data class Period(

    @SerializedName("_links") val _links: Links,
    @SerializedName("id") val id: Int,
    @SerializedName("start_timestamp") val start_timestamp: Long,
    @SerializedName("end_timestamp") val end_timestamp: Long
)
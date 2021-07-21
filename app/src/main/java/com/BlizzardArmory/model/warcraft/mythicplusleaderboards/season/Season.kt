package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class Season(

    @SerializedName("_links") val links: Links,
    @SerializedName("id") val id: Int,
    @SerializedName("start_timestamp") val start_timestamp: Long,
    @SerializedName("end_timestamp") val end_timestamp: Long,
    @SerializedName("periods") val periods: List<Periods>
)
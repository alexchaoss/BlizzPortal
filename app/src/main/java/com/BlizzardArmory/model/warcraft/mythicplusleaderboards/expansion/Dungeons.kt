package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.expansion

import com.google.gson.annotations.SerializedName


data class Dungeons(

    @SerializedName("id") val id: Long,
    @SerializedName("challenge_mode_id") val challenge_mode_id: Long,
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("short_name") val short_name: String
)
package com.BlizzardArmory.model.warcraft.guild.achievements

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Achievement(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)
package com.BlizzardArmory.model.warcraft.covenant.soulbind

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class TechTalentTree(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)
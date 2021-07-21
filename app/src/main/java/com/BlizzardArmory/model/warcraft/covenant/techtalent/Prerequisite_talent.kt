package com.BlizzardArmory.model.warcraft.covenant.techtalent

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Prerequisite_talent(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
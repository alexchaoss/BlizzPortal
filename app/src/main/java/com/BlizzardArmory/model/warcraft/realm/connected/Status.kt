package com.BlizzardArmory.model.warcraft.realm.connected

import com.google.gson.annotations.SerializedName


data class Status(

    @SerializedName("name") val name: Name,
    @SerializedName("type") val type: String
)
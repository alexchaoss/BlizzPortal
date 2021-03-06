package com.BlizzardArmory.model.warcraft.realm.connected

import com.google.gson.annotations.SerializedName


data class Region(

    @SerializedName("name") val name: Name,
    @SerializedName("id") val id: Int
)
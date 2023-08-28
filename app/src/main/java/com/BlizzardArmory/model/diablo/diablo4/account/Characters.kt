package com.BlizzardArmory.model.diablo.diablo4.account

import com.google.gson.annotations.SerializedName


data class Characters(
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("class") var charClass: String? = null,
    @SerializedName("level") var level: Int? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null
)
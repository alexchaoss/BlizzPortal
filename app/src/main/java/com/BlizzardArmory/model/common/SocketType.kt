package com.BlizzardArmory.model.common

import com.google.gson.annotations.SerializedName


data class SocketType(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
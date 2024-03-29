package com.BlizzardArmory.model.warcraft.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SocketType(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
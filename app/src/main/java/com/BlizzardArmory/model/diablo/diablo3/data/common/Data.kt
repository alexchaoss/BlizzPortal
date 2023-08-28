package com.BlizzardArmory.model.diablo.diablo3.data.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Data(

    @SerializedName("id") val id: String,
    @SerializedName("number") val number: Int,
    @SerializedName("string") val string: String?,
    @SerializedName("timestamp") val timestamp: Long
)
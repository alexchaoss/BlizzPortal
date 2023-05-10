package com.BlizzardArmory.model.diablo.data.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Column(

    @SerializedName("id") val id: String,
    @SerializedName("hidden") val hidden: Boolean,
    @SerializedName("order") val order: Int,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String
)
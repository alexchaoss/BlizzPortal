package com.BlizzardArmory.model.diablo.data.common

import com.google.gson.annotations.SerializedName


data class Column(

    @SerializedName("id") val id: String,
    @SerializedName("hidden") val hidden: Boolean,
    @SerializedName("order") val order: Int,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String
)
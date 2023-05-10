package com.BlizzardArmory.model.starcraft

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CurrentSeason(
    @SerializedName("seasonId")
    val seasonId: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("year")
    val year: Int,
    @SerializedName("startDate")
    val startDate: Long,
    @SerializedName("endDate")
    val endDate: Long
)
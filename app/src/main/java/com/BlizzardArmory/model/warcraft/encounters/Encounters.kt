package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Encounters(

    @SerializedName("encounter") val encounter: Encounter,
    @SerializedName("completed_count") val completed_count: Int,
    @SerializedName("last_kill_timestamp") val last_kill_timestamp: Long
)
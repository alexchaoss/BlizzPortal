package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Progress(

    @SerializedName("completed_count") val completed_count: Int,
    @SerializedName("total_count") val total_count: Int,
    @SerializedName("encounters") val encounters: List<Encounters>
)
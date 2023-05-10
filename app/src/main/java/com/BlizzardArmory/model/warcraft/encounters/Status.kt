package com.BlizzardArmory.model.warcraft.encounters

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Status(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
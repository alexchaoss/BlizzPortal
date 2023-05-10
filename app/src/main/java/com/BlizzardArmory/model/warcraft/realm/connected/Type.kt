package com.BlizzardArmory.model.warcraft.realm.connected

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Type(

    @SerializedName("name") val name: Name,
    @SerializedName("type") val type: String
)
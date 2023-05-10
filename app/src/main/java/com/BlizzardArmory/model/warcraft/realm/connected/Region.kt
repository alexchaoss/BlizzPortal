package com.BlizzardArmory.model.warcraft.realm.connected

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Region(

    @SerializedName("name") val name: Name,
    @SerializedName("id") val id: Int
)
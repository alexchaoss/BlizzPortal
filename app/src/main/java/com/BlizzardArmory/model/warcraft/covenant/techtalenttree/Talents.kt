package com.BlizzardArmory.model.warcraft.covenant.techtalenttree

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Talents(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
)
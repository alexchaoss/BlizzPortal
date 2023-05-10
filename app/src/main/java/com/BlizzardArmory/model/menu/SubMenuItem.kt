package com.BlizzardArmory.model.menu

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SubMenuItem(
    @SerializedName("icon") val icon: String,
    @SerializedName("string") val string: String,
    @SerializedName("parent") val parent: String,
    @SerializedName("private") val private: Boolean = false,
)
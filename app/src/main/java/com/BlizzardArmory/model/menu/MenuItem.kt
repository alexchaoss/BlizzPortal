package com.BlizzardArmory.model.menu

import com.google.gson.annotations.SerializedName

data class MenuItem(
    @SerializedName("icon") val icon: String,
    @SerializedName("string") val string: String,
    @SerializedName("sub_menu") val sub_menu: List<SubMenuItem>,
)
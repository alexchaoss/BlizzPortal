package com.BlizzardArmory.model.menu

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Menu(@SerializedName("menu") val menuList: List<MenuItem>)

package com.BlizzardArmory.model.menu

import com.google.gson.annotations.SerializedName

data class Menu(@SerializedName("menu") val menuList: List<MenuItem>)

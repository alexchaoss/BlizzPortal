package com.BlizzardArmory.model.help

import com.google.gson.annotations.SerializedName

data class Help(@SerializedName("help") val helpItemList: List<HelpItem>)

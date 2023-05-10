package com.BlizzardArmory.model.help

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Help(@SerializedName("help") val helpItemList: List<HelpItem>)

package com.BlizzardArmory.model.help

import com.google.gson.annotations.SerializedName

data class HelpItem(
    @SerializedName("title") val title: String,
    @SerializedName("contentText") var content: String,
    @SerializedName("imageSrc") var imageSrc: String,
)

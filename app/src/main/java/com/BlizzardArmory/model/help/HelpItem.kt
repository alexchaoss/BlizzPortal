package com.BlizzardArmory.model.help

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HelpItem(
    @SerializedName("title") val title: String,
    @SerializedName("contentText") var content: String,
    @SerializedName("imageSrc") var imageSrc: String,
)

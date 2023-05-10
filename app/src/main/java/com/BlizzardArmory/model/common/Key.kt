package com.BlizzardArmory.model.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Key(

    @SerializedName("href") val href: String
)
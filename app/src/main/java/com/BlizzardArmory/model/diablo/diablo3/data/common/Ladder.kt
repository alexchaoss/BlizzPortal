package com.BlizzardArmory.model.diablo.diablo3.data.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Ladder(

    @SerializedName("href") val href: String
)
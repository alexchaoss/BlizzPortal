package com.BlizzardArmory.model.diablo.diablo3.data.seasons.index

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Season(

    @SerializedName("href") val href: String
)
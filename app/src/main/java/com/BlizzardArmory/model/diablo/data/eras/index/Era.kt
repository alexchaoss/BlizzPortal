package com.BlizzardArmory.model.diablo.data.eras.index

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Era(

    @SerializedName("href") val href: String
)
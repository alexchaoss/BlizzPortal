package com.BlizzardArmory.model.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Links(

    @SerializedName("self") val self: Self
)
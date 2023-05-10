package com.BlizzardArmory.model.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Media(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
package com.BlizzardArmory.model.diablo.data.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Player(

    @SerializedName("key") val key: Int,
    @SerializedName("accountId") val accountId: Int,
    @SerializedName("data") val data: List<Data>
)
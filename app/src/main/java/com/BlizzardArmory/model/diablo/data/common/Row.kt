package com.BlizzardArmory.model.diablo.data.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Row(

    @SerializedName("player") val player: List<Player>,
    @SerializedName("order") val order: Int,
    @SerializedName("data") val data: List<Data>
)
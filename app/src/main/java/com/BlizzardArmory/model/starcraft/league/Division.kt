package com.BlizzardArmory.model.starcraft.league

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Division(

    @SerializedName("id") val id: Int,
    @SerializedName("ladder_id") val ladder_id: Int,
    @SerializedName("member_count") val member_count: Int
)
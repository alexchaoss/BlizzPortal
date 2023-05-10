package com.BlizzardArmory.model.starcraft.league

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Tier(

    @SerializedName("id") val id: Int,
    @SerializedName("division") val division: List<Division>
)
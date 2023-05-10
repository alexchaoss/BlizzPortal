package com.BlizzardArmory.model.warcraft.covenant.covenant

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class RenownRewards(

    @SerializedName("level") val level: Int,
    @SerializedName("reward") val reward: Reward
)
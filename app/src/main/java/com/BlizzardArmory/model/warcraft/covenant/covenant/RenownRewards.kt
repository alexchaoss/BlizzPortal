package com.BlizzardArmory.model.warcraft.covenant.covenant

import com.google.gson.annotations.SerializedName


data class RenownRewards(

    @SerializedName("level") val level: Int,
    @SerializedName("reward") val reward: Reward
)
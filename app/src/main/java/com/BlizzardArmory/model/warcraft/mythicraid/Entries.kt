package com.BlizzardArmory.model.warcraft.mythicraid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Entries(
    @SerializedName("guild") val guild: Guild,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("region") val region: String,
    @SerializedName("rank") val rank: Int
)
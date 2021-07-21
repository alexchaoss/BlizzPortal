package com.BlizzardArmory.model.warcraft.pvp.leaderboards.season

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Current_season(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
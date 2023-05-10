package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.season.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Current_season(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
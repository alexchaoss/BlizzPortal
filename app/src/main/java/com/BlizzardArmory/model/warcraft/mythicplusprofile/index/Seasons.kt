package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Seasons(
    @SerializedName("key") var key: Key,
    @SerializedName("id") var id: Int
)
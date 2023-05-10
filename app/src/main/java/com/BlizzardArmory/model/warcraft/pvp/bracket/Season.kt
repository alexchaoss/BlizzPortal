package com.BlizzardArmory.model.warcraft.pvp.bracket

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Season(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
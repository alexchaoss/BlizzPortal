package com.BlizzardArmory.model.warcraft.reputations.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Root_factions(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
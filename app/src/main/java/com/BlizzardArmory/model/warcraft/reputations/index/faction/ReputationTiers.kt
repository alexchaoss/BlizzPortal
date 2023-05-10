package com.BlizzardArmory.model.warcraft.reputations.index.faction

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class ReputationTiers(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
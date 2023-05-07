package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Seasons(
    @SerializedName("key") var key: Key? = null,
    @SerializedName("id") var id: Int? = null
)
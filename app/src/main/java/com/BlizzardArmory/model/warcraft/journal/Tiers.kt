package com.BlizzardArmory.model.warcraft.journal

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Tiers(

    @SerializedName("key") var key: Key,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: Int

)
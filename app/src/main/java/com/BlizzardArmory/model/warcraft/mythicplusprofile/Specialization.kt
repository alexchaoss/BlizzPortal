package com.BlizzardArmory.model.warcraft.mythicplusprofile

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Specialization(

    @SerializedName("key") var key: Key? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null

)
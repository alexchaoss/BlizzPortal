package com.BlizzardArmory.model.warcraft.mythicplusprofile.season

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Season(
    @SerializedName("key") var key: Key? = null,
    @SerializedName("id") var id: Int? = null
)
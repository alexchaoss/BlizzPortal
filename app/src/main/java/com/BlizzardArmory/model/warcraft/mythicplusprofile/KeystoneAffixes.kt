package com.BlizzardArmory.model.warcraft.mythicplusprofile

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class KeystoneAffixes(

    @SerializedName("key") var key: Key,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: Int

)
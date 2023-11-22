package com.BlizzardArmory.model.warcraft.mythicraid

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.Realm
import com.google.gson.annotations.SerializedName


@Keep
data class Guild(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Realm
)
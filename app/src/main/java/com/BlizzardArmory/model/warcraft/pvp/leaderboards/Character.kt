package com.BlizzardArmory.model.warcraft.pvp.leaderboards

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName

@Keep
data class Character(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Realm
)
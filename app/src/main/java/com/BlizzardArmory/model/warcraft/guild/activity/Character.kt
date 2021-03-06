package com.BlizzardArmory.model.warcraft.guild.activity

import com.BlizzardArmory.model.common.Key
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName


data class Character(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long,
    @SerializedName("realm") val realm: Realm
)
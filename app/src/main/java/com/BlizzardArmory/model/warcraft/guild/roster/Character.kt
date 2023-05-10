package com.BlizzardArmory.model.warcraft.guild.roster

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName


@Keep
data class Character(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("level") val level: Int,
    @SerializedName("playable_class") val playable_class: Playable_class,
    @SerializedName("playable_race") val playable_race: Playable_race
)
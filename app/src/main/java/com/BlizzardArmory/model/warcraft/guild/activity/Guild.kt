package com.BlizzardArmory.model.warcraft.guild.activity

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Faction
import com.BlizzardArmory.model.common.Key
import com.BlizzardArmory.model.common.Realm
import com.google.gson.annotations.SerializedName


@Keep
data class Guild(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("faction") val faction: Faction
)
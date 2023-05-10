package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

@Keep
data class Soulbind(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)
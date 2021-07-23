package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName

data class Trait(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
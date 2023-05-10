package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Type(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)
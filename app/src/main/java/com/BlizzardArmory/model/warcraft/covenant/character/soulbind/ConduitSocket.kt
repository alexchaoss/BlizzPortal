package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ConduitSocket(

    @SerializedName("type") val type: Type,
    @SerializedName("socket") val socket: Socket?
)
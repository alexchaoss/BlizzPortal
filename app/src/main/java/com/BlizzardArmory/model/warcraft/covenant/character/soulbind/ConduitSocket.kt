package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class ConduitSocket(

    @SerializedName("type") val type: Type,
    @SerializedName("socket") val socket: Socket?
)
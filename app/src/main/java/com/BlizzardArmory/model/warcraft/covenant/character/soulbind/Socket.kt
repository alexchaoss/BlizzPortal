package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import com.google.gson.annotations.SerializedName


data class Socket(

    @SerializedName("conduit") val conduit: Conduit,
    @SerializedName("rank") val rank: Int
)
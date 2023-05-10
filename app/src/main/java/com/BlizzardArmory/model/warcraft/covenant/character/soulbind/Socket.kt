package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Socket(

    @SerializedName("conduit") val conduit: Conduit,
    @SerializedName("rank") val rank: Int
)
package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Traits(

    @SerializedName("trait") val trait: Trait,
    @SerializedName("conduit_socket") val conduitSocket: ConduitSocket?,
    @SerializedName("tier") val tier: Int,
    @SerializedName("display_order") val displayOrder: Int
)
package com.BlizzardArmory.model.warcraft.covenant.character.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Soulbinds(

    @SerializedName("soulbind") val soulbind: Soulbind,
    @SerializedName("traits") val traits: List<Traits>
)
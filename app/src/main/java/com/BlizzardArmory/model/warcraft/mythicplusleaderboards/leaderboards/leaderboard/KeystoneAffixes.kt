package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class KeystoneAffixes(

    @SerializedName("keystone_affix") val keystone_affixstone_affix: KeystoneAffix,
    @SerializedName("starting_level") val starting_level: Int
)
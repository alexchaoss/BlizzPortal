package com.BlizzardArmory.warcraft.pvp.bracket

import com.google.gson.annotations.SerializedName

data class Tier(

        @SerializedName("key") val key: Key,
        @SerializedName("id") val id: Int
)
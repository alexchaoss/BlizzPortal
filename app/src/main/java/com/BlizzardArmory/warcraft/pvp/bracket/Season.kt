package com.BlizzardArmory.warcraft.pvp.bracket

import com.google.gson.annotations.SerializedName

data class Season(

        @SerializedName("key") val key: Key,
        @SerializedName("id") val id: Int
)
package com.BlizzardArmory.model.warcraft.guild.roster

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Playable_race(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
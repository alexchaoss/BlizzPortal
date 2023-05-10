package com.BlizzardArmory.model.warcraft.guild.roster

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class Playable_race(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)
package com.BlizzardArmory.model.warcraft.covenant.soulbind

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Follower(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)
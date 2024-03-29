package com.BlizzardArmory.model.starcraft.leaderboard

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Character(

    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Int,
    @SerializedName("region") val region: Int,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("clanName") val clanName: String,
    @SerializedName("clanTag") val clanTag: String,
    @SerializedName("profilePath") val profilePath: String
)
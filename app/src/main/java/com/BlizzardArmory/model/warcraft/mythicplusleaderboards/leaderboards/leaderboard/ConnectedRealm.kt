package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ConnectedRealm(

    @SerializedName("href") val href: String
)
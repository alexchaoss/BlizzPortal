package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.mythicraid.Faction
import com.google.gson.annotations.SerializedName

@Keep
data class Members(

    @SerializedName("profile") val profile: Profile,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("specialization") val specialization: Specialization
)
package com.BlizzardArmory.model.starcraft.leaderboard

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class LadderTeam(

    @SerializedName("ladderMembers") val ladderMembers: List<LadderMembers>
)
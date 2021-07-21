package com.BlizzardArmory.model.starcraft.leaderboard

import com.google.gson.annotations.SerializedName


data class LadderTeam(

    @SerializedName("ladderMembers") val ladderMembers: List<LadderMembers>
)
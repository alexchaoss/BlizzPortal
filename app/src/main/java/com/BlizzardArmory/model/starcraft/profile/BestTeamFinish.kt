package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Best team finish.
 */
data class BestTeamFinish(

        @SerializedName("leagueName")
        var leagueName: String,

        @SerializedName("timesAchieved")
        var timesAchieved: Int

)
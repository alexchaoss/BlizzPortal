package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Best team finish.
 */
data class BestTeamFinish(

        @SerializedName("leagueName")
        @Expose
        var leagueName: String,

        @SerializedName("timesAchieved")
        @Expose
        var timesAchieved: Int

)
package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Best 1 v 1 finish.
 */
data class Best1v1Finish(

        @SerializedName("leagueName")
        var leagueName: String,

        @SerializedName("timesAchieved")
        var timesAchieved: Int

)
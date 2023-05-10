package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Best team finish.
 */
@Keep
data class BestTeamFinish(

    @SerializedName("leagueName")
    var leagueName: String?,

    @SerializedName("timesAchieved")
    var timesAchieved: Int

)
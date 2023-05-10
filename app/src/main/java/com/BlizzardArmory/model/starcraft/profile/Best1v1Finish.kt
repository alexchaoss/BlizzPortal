package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Best 1 v 1 finish.
 */
@Keep
data class Best1v1Finish(

    @SerializedName("leagueName")
    var leagueName: String?,

    @SerializedName("timesAchieved")
    var timesAchieved: Int

)
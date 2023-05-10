package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Elite kills.
 */
@Keep
data class EliteKills(

    @SerializedName("elites")
    var elites: Int

)
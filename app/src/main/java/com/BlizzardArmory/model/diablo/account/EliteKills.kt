package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Elite kills.
 */
data class EliteKills(

    @SerializedName("elites")
    var elites: Int

)
package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Kills.
 */
@Keep
data class Kills(

    @SerializedName("monsters")
    var monsters: Int,

    @SerializedName("elites")
    var elites: Int,


    @SerializedName("hardcoreMonsters")
    var hardcoreMonsters: Int

)
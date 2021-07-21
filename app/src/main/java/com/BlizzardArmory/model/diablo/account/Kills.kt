package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Kills.
 */
data class Kills(

    @SerializedName("monsters")
    var monsters: Int,

    @SerializedName("elites")
    var elites: Int,


    @SerializedName("hardcoreMonsters")
    var hardcoreMonsters: Int

)
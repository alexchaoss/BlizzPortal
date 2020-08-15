package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Kills.
 */
data class Kills(

        @SerializedName("monsters")
        @Expose
        var monsters: Int,

        @SerializedName("elites")
        @Expose
        var elites: Int,


        @SerializedName("hardcoreMonsters")
        @Expose
        var hardcoreMonsters: Int

)
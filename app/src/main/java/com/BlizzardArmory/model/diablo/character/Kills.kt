package com.BlizzardArmory.model.diablo.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Kills.
 */
data class Kills(

        @SerializedName("elites")
        @Expose
        var elites: Int? = null

)
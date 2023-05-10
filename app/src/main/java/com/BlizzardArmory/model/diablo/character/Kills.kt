package com.BlizzardArmory.model.diablo.character

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Kills.
 */
@Keep
data class Kills(

    @SerializedName("elites")
    var elites: Int? = null

)
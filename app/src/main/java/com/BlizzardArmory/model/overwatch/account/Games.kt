package com.BlizzardArmory.model.overwatch.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Games.
 */
@Keep
data class Games(

    @SerializedName("played")
    var played: Int,

    @SerializedName("won")
    var won: Int

)
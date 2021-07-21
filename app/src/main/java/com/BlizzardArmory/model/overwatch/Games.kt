package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.SerializedName


/**
 * The type Games.
 */
data class Games(

    @SerializedName("played")
    var played: Int,

    @SerializedName("won")
    var won: Int

)
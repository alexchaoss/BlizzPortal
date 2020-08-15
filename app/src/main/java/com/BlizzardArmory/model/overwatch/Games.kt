package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Games.
 */
data class Games(

        @SerializedName("played")
        @Expose
        var played: Int,

        @SerializedName("won")
        @Expose
        var won: Int

)
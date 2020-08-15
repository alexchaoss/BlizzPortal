package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Death.
 */
data class Death(

        @SerializedName("killer")
        @Expose
        var killer: Int,

        @SerializedName("time")
        @Expose
        var time: Int
)
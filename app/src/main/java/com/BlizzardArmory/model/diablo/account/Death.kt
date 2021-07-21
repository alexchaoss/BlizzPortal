package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Death.
 */
data class Death(

    @SerializedName("killer")
    var killer: Int,

    @SerializedName("time")
    var time: Int
)
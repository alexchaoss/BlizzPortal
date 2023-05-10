package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Death.
 */
@Keep
data class Death(

    @SerializedName("killer")
    var killer: Int,

    @SerializedName("time")
    var time: Int
)
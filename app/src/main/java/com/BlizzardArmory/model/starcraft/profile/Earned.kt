package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Earned.
 */
@Keep
data class Earned(

    @SerializedName("quantity")
    var quantity: Int,

    @SerializedName("startTime")
    var startTime: Int

)
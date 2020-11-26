package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Earned.
 */
data class Earned(

        @SerializedName("quantity")
        var quantity: Int,

        @SerializedName("startTime")
        var startTime: Int

)
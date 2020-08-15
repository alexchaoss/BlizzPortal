package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Earned.
 */
data class Earned(

        @SerializedName("quantity")
        @Expose
        var quantity: Int,

        @SerializedName("startTime")
        @Expose
        var startTime: Int

)
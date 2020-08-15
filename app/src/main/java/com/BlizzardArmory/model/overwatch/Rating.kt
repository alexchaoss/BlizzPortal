package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Rating.
 */
data class Rating(

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("role")
        @Expose
        var role: String,

        @SerializedName("roleIcon")
        @Expose
        var roleIcon: String,

        @SerializedName("rankIcon")
        @Expose
        var rankIcon: String

)
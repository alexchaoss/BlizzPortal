package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


/**
 * The type Rating.
 */
data class Rating(

    @SerializedName("level")
    var level: Int,

    @SerializedName("role")
    var role: String,

    @SerializedName("roleIcon")
    var roleIcon: String,

    @SerializedName("rankIcon")
    var rankIcon: String

)
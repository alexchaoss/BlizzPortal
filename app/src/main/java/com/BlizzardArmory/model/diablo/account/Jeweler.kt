package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler.
 */
data class Jeweler(

        @SerializedName("slug")
        var slug: String,

        @SerializedName("level")
        var level: Int
)

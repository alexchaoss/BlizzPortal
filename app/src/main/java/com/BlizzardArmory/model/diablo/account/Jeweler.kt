package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Jeweler.
 */
data class Jeweler(

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("level")
        @Expose
        var level: Int
)

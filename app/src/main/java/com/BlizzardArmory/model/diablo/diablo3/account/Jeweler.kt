package com.BlizzardArmory.model.diablo.diablo3.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler.
 */
@Keep
data class Jeweler(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("level")
    var level: Int
)

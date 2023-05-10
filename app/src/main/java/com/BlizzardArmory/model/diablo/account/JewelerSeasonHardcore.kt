package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler season hardcore.
 */
@Keep
data class JewelerSeasonHardcore(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("level")
    var level: Int

)
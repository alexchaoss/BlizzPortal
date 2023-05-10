package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler season.
 */
@Keep
data class JewelerSeason(

    @SerializedName("slug")
    var slug: String,


    @SerializedName("level")
    var level: Int

)
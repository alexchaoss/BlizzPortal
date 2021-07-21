package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler season.
 */
data class JewelerSeason(

    @SerializedName("slug")
    var slug: String,


    @SerializedName("level")
    var level: Int

)
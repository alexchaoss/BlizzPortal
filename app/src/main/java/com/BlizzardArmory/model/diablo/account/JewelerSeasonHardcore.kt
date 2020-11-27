package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Jeweler season hardcore.
 */
data class JewelerSeasonHardcore(

        @SerializedName("slug")
        var slug: String,

        @SerializedName("level")
        var level: Int

)
package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Blacksmith season hardcore.
 */
data class BlacksmithSeasonHardcore(

        @SerializedName("slug")
        var slug: String,

        @SerializedName("level")
        var level: Int

)
package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Blacksmith season.
 */
data class BlacksmithSeason(

        @SerializedName("slug")
        var slug: String,


        @SerializedName("level")
        var level: Int

)
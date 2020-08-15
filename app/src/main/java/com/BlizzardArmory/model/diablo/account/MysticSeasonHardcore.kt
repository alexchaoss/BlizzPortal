package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Mystic season hardcore.
 */
data class MysticSeasonHardcore(

        @SerializedName("slug")
        @Expose
        var slug: String,


        @SerializedName("level")
        @Expose
        var level: Int
)
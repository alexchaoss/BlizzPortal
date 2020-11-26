package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Mystic.
 */
data class Mystic(

        @SerializedName("slug")
        var slug: String,


        @SerializedName("level")
        var level: Int
)
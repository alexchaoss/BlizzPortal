package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Mystic hardcore.
 */
data class MysticHardcore(

        @SerializedName("slug")
        var slug: String,


        @SerializedName("level")
        var level: Int
)
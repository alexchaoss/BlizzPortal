package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Mystic season hardcore.
 */
data class MysticSeasonHardcore(

    @SerializedName("slug")
    var slug: String,


    @SerializedName("level")
    var level: Int
)
package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Mystic hardcore.
 */
@Keep
data class MysticHardcore(

    @SerializedName("slug")
    var slug: String,


    @SerializedName("level")
    var level: Int
)
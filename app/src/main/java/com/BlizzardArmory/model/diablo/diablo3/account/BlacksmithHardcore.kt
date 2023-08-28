package com.BlizzardArmory.model.diablo.diablo3.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Blacksmith hardcore.
 */
@Keep
data class BlacksmithHardcore(

    @SerializedName("slug")
    var slug: String,


    @SerializedName("level")
    var level: Int? = null

)
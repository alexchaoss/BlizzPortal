package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Blacksmith season hardcore.
 */
@Keep
data class BlacksmithSeasonHardcore(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("level")
    var level: Int

)
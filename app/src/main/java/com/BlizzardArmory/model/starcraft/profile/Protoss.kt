package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Protoss.
 */
@Keep
data class Protoss(

    @SerializedName("level")
    var level: Int,

    @SerializedName("maxLevelPoints")
    var maxLevelPoints: Int,

    @SerializedName("currentLevelPoints")
    var currentLevelPoints: Int

)
package com.BlizzardArmory.model.warcraft.statistic

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Agility.
 */
@Keep
data class Agility(

    @SerializedName("base")
    var base: Int,

    @SerializedName("effective")
    var effective: Int

)
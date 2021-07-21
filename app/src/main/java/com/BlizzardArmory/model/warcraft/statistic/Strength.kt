package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Strength.
 */
class Strength(

    @SerializedName("base")
    var base: Int,

    @SerializedName("effective")
    var effective: Int

)
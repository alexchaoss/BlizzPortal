package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Agility.
 */
data class Agility(

    @SerializedName("base")
    var base: Int,

    @SerializedName("effective")
    var effective: Int

)
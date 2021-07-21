package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Stamina.
 */
class Stamina(

    @SerializedName("base")
    var base: Int,

    @SerializedName("effective")
    var effective: Int

)
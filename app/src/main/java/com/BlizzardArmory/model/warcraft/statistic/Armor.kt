package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.SerializedName


/**
 * The type Armor.
 */
data class Armor(

    @SerializedName("base")
    var base: Int,

    @SerializedName("effective")
    var effective: Int

)
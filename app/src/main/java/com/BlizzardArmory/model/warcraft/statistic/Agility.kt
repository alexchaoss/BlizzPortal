package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Agility.
 */
data class Agility(

        @SerializedName("base")
        @Expose
        var base: Int,

        @SerializedName("effective")
        @Expose
        var effective: Int

)
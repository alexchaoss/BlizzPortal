package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Stamina.
 */
class Stamina(

        @SerializedName("base")
        @Expose
        var base: Int,

        @SerializedName("effective")
        @Expose
        var effective: Int

)
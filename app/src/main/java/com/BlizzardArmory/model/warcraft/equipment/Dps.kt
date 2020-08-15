package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Dps.
 */
data class Dps(

        @SerializedName("value")
        @Expose
        var value: Float,

        @SerializedName("display_string")
        @Expose
        var displayString: String

)
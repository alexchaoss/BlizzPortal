package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Dps.
 */
data class Dps(

    @SerializedName("value")
    var value: Float,

    @SerializedName("display_string")
    var displayString: String

)
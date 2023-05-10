package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Dps.
 */
@Keep
data class Dps(

    @SerializedName("value")
    var value: Float,

    @SerializedName("display_string")
    var displayString: String

)
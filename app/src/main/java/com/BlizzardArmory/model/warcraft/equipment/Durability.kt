package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Durability.
 */
@Keep
data class Durability(

    @SerializedName("value")
    var value: Int,

    @SerializedName("display_string")
    var displayString: String

)
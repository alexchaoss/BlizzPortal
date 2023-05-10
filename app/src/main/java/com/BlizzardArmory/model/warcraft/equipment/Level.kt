package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Level.
 */
@Keep
data class Level(

    @SerializedName("value")
    var value: Int,

    @SerializedName("display_string")
    var displayString: String

)
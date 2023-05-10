package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Display.
 */
@Keep
data class Display(

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("color")
    var color: Color

)
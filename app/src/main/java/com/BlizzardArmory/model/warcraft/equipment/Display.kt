package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Display.
 */
data class Display(

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("color")
    var color: Color

)
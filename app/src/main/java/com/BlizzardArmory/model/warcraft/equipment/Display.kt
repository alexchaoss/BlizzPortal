package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Display.
 */
data class Display(

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("color")
        @Expose
        var color: Color

)
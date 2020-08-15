package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Name description.
 */
data class NameDescription(

        @field:Expose
        @field:SerializedName("display_string")
        var displayString: String,

        @field:Expose
        @field:SerializedName("color")
        var color: Color
)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Armor.
 */
data class Armor(

        @SerializedName("value")
        @Expose
        var value: Int,

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("display")
        @Expose
        var display: Display

)
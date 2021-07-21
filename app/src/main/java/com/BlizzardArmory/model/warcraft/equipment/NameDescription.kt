package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Name description.
 */
data class NameDescription(

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("color")
    var color: Color
)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Level.
 */
data class Level(

    @SerializedName("value")
    var value: Int,

    @SerializedName("display_string")
    var displayString: String

)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Armor.
 */
data class Armor(

    @SerializedName("value")
    var value: Int,

    @SerializedName("display_string")
    var displayString: String?,

    @SerializedName("display")
    var display: Display

)
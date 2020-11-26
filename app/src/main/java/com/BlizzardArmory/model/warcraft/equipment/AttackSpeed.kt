package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Attack speed.
 */
class AttackSpeed(

        @SerializedName("value")
        var value: Int,

        @SerializedName("display_string")
        var displayString: String

)
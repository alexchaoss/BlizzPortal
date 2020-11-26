package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Faction.
 */
data class Faction(

        @SerializedName("value")
        var value: Value,

        @SerializedName("display_string")
        var displayString: String

)
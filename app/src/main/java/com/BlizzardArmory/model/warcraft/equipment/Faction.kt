package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Faction.
 */
data class Faction(

        @SerializedName("value")
        @Expose
        var value: Value,

        @SerializedName("display_string")
        @Expose
        var displayString: String

)
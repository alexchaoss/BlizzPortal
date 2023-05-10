package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Faction.
 */
@Keep
data class Faction(

    @SerializedName("value")
    var value: Value,

    @SerializedName("display_string")
    var displayString: String

)
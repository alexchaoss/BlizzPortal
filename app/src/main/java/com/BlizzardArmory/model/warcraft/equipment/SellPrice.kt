package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Sell price.
 */
data class SellPrice(

        @SerializedName("value")
        @Expose
        var value: Int,

        @SerializedName("display_strings")
        @Expose
        var displayStrings: DisplayStrings

)
package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Sell price.
 */
@Keep
data class SellPrice(

    @SerializedName("value")
    var value: Int,

    @SerializedName("display_strings")
    var displayStrings: DisplayStrings

)
package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Display strings.
 */
@Keep
data class DisplayStrings(

    @SerializedName("header")
    var header: String,

    @SerializedName("gold")
    var gold: String,

    @SerializedName("silver")
    var silver: String,

    @SerializedName("copper")
    var copper: String

)
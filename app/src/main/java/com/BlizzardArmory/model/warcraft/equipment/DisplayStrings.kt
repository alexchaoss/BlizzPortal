package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Display strings.
 */
data class DisplayStrings(

        @SerializedName("header")
        @Expose
        var header: String,

        @SerializedName("gold")
        @Expose
        var gold: String,

        @SerializedName("silver")
        @Expose
        var silver: String,

        @SerializedName("copper")
        @Expose
        var copper: String

)
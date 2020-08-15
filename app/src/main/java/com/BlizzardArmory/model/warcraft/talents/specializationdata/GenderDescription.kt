package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Gender description.
 */
data class GenderDescription(

        @SerializedName("male")
        @Expose
        var male: String,

        @SerializedName("female")
        @Expose
        var female: String

)
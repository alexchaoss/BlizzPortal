package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Criterium.
 */
data class Criterium(

        @SerializedName("criterionId")
        @Expose
        var criterionId: String,

        @SerializedName("earned")
        @Expose
        var earned: Earned

)
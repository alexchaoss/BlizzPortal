package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Criterium.
 */
data class Criterium(

        @SerializedName("criterionId")
        var criterionId: String,

        @SerializedName("earned")
        var earned: Earned

)
package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Criterium.
 */
@Keep
data class Criterium(

    @SerializedName("criterionId")
    var criterionId: String,

    @SerializedName("earned")
    var earned: Earned

)
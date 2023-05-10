package com.BlizzardArmory.model.common

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Faction.
 */
@Keep
data class Faction(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
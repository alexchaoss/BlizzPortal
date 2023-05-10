package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Value.
 */
@Keep
data class Value(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
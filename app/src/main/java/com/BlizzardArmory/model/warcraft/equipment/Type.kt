package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Type.
 */
@Keep
data class Type(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
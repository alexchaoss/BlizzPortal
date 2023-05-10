package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Binding.
 */
@Keep
data class Binding(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
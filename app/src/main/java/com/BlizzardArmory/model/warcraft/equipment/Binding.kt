package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Binding.
 */
data class Binding(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Value.
 */
data class Value(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
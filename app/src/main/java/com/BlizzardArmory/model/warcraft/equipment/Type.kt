package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Type.
 */
data class Type(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
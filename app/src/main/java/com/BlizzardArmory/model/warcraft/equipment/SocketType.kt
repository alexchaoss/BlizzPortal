package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Socket type.
 */
data class SocketType(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
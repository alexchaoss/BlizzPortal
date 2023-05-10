package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Socket type.
 */
@Keep
data class SocketType(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
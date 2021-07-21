package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Slot.
 */
data class Slot(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
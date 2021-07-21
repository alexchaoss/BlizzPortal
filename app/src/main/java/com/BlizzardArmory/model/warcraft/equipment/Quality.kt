package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Quality.
 */
data class Quality(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
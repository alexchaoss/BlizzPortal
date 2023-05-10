package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Quality.
 */
@Keep
data class Quality(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
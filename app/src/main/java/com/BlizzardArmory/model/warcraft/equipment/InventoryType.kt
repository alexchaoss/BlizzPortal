package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Inventory type.
 */
@Keep
data class InventoryType(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Inventory type.
 */
data class InventoryType(

        @SerializedName("type")
        var type: String,

        @SerializedName("name")
        var name: String

)
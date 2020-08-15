package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Item.
 */
data class Item(

        @SerializedName("key")
        @Expose
        var key: Key,

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("name")
        @Expose
        var name: String?

)
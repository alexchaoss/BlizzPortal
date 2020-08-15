package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Item equipped.
 */
data class ItemEquipped(

        @SerializedName("item")
        @Expose
        var item: Item,

        @SerializedName("is_equipped")
        @Expose
        var isIsEquipped: Boolean
)
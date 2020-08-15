package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Set.
 */
data class Set(

        @SerializedName("item_set")
        @Expose
        var itemSet: ItemSet,

        @SerializedName("items")
        @Expose
        var items: List<ItemEquipped>,

        @SerializedName("effects")
        @Expose
        var effects: List<Effect>,

        @SerializedName("display_string")
        @Expose
        var displayString: String

)
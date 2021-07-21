package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Set.
 */
data class Set(

    @SerializedName("item_set")
    var itemSet: ItemSet,

    @SerializedName("items")
    var items: List<ItemEquipped>,

    @SerializedName("effects")
    var effects: List<Effect>,

    @SerializedName("display_string")
    var displayString: String

)
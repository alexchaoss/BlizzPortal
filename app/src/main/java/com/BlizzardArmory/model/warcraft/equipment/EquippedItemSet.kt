package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Equipped item set.
 */
@Keep
data class EquippedItemSet(

    @SerializedName("item_set")
    var itemSet: ItemSet,

    @SerializedName("items")
    var items: List<Item>,

    @SerializedName("effects")
    var effects: List<Effect>,

    @SerializedName("display_string")
    var displayString: String

)
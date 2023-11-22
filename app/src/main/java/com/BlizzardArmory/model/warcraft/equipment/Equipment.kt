package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.BlizzardArmory.model.warcraft.common.Character
import com.google.gson.annotations.SerializedName


/**
 * The type Equipment.
 */
@Keep
data class Equipment(

    @SerializedName("_links")
    var links: Links,

    @SerializedName("character")
    var character: Character,

    @SerializedName("equipped_items")
    var equippedItems: List<EquippedItem>,

    @SerializedName("equipped_item_sets")
    var equippedItemSets: List<EquippedItemSet>

)
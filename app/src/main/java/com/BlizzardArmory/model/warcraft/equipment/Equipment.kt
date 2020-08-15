package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Equipment.
 */
data class Equipment(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("character")
        @Expose
        var character: Character,

        @SerializedName("equipped_items")
        @Expose
        var equippedItems: List<EquippedItem>,

        @SerializedName("equipped_item_sets")
        @Expose
        var equippedItemSets: List<EquippedItemSet>

)
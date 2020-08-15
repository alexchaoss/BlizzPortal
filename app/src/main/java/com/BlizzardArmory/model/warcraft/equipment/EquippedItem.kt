package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Equipped item.
 */
data class EquippedItem(

        @SerializedName("item")
        @Expose
        var item: Item,

        @SerializedName("slot")
        @Expose
        var slot: Slot,

        @SerializedName("quantity")
        @Expose
        var quantity: Int,

        @SerializedName("context")
        @Expose
        var context: Int,

        @SerializedName("bonus_list")
        @Expose
        var bonusList: List<Int>,

        @SerializedName("quality")
        @Expose
        var quality: Quality,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("modified_appearance_id")
        @Expose
        var modifiedAppearanceId: Long,

        @SerializedName("azerite_details")
        @Expose
        var azeriteDetails: AzeriteDetails,

        @SerializedName("media")
        @Expose
        var media: Media,

        @SerializedName("item_class")
        @Expose
        var itemClass: ItemClass,

        @SerializedName("item_subclass")
        @Expose
        var itemSubclass: ItemSubclass,

        @SerializedName("inventory_type")
        @Expose
        var inventoryType: InventoryType,

        @SerializedName("binding")
        @Expose
        var binding: Binding,

        @SerializedName("armor")
        @Expose
        var armor: Armor,

        @SerializedName("stats")
        @Expose
        var stats: List<Stat>,

        @SerializedName("requirements")
        @Expose
        var requirements: Requirements,

        @SerializedName("level")
        @Expose
        var level: Level,

        @SerializedName("transmog")
        @Expose
        var transmog: Transmog,

        @SerializedName("durability")
        @Expose
        var durability: Durability,

        @SerializedName("unique_equipped")
        @Expose
        var uniqueEquipped: String,

        @SerializedName("spells")
        @Expose
        var spells: List<SpellDescription>,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("is_subclass_hidden")
        @Expose
        var isIsSubclassHidden: Boolean,

        @SerializedName("set")
        @Expose
        var set: Set,

        @SerializedName("name_description")
        @Expose
        var nameDescriptionObject: NameDescription,

        @SerializedName("socket_bonus")
        @Expose
        var socketBonus: String,

        @SerializedName("sell_price")
        @Expose
        var sellPrice: SellPrice,

        @SerializedName("sockets")
        @Expose
        var sockets: List<Socket>,

        @SerializedName("enchantments")
        @Expose
        var enchantments: List<Enchantment>,

        @SerializedName("weapon")
        @Expose
        var weapon: Weapon

)
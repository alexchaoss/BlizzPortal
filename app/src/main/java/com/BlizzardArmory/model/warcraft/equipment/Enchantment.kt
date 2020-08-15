package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Enchantment.
 */
data class Enchantment(

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("enchantment_id")
        @Expose
        var enchantmentId: Long,

        @SerializedName("enchantment_slot")
        @Expose
        var enchantmentSlot: EnchantmentSlot

)
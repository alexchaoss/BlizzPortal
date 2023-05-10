package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Enchantment.
 */
@Keep
data class Enchantment(

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("enchantment_id")
    var enchantmentId: Long,

    @SerializedName("enchantment_slot")
    var enchantmentSlot: EnchantmentSlot

)
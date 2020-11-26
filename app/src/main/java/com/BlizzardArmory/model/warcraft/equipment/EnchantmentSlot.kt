package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Enchantment slot.
 */
data class EnchantmentSlot(

        @SerializedName("id")
        var id: Long,

        @SerializedName("type")
        var type: String

)
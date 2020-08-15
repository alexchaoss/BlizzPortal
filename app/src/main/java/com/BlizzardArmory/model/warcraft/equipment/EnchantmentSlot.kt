package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Enchantment slot.
 */
data class EnchantmentSlot(

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("type")
        @Expose
        var type: String

)
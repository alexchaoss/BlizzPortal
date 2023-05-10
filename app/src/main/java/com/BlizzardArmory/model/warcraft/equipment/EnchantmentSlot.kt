package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Enchantment slot.
 */
@Keep
data class EnchantmentSlot(

    @SerializedName("id")
    var id: Long,

    @SerializedName("type")
    var type: String

)
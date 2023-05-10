package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Item equipped.
 */
@Keep
data class ItemEquipped(

    @SerializedName("item")
    var item: Item,

    @SerializedName("is_equipped")
    var isIsEquipped: Boolean
)
package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


/**
 * The type Item subclass.
 */
@Keep
data class ItemSubclass(

    @SerializedName("key")
    var key: Key,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long

)
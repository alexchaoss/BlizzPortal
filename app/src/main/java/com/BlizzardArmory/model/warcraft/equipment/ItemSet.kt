package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Item set.
 */
@Keep
data class ItemSet(

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long

)
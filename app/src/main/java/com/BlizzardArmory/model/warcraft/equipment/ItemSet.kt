package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Item set.
 */
data class ItemSet(

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long

)
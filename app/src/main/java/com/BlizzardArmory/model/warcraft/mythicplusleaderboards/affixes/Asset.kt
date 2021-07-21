package com.BlizzardArmory.model.warcraft.mythicplusleaderboards.affixes

import com.google.gson.annotations.SerializedName


/**
 * The type Asset.
 */
data class Asset(

    @SerializedName("key")
    var key: String,

    @SerializedName("value")
    var value: String,

    @SerializedName("file_data_id")
    var file_data_id: Long

)
package com.BlizzardArmory.model.warcraft.equipment.media

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Asset.
 */
data class Asset(

        @SerializedName("key")
        @Expose
        var key: String,

        @SerializedName("value")
        @Expose
        var value: String

)
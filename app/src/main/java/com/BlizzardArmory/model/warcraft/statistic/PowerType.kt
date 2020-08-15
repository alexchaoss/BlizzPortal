package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Power type.
 */
data class PowerType(

        @SerializedName("key")
        @Expose
        var key: Key,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("id")
        @Expose
        var id: Long

)
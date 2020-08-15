package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Gem info.
 */
data class GemInfo(
        @SerializedName("id")
        @Expose
        var id: String,

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("icon")
        @Expose
        var icon: String,

        @SerializedName("path")
        @Expose
        var path: String

)
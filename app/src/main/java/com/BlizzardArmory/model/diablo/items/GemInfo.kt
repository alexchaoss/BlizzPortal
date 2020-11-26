package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.SerializedName


/**
 * The type Gem info.
 */
data class GemInfo(
        @SerializedName("id")
        var id: String,

        @SerializedName("slug")
        var slug: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("icon")
        var icon: String,

        @SerializedName("path")
        var path: String

)
package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Set.
 */
data class Set(

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("descriptionHtml")
        @Expose
        var descriptionHtml: String

)
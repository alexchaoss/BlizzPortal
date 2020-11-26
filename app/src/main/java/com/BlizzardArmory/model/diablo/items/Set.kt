package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.SerializedName


/**
 * The type Set.
 */
data class Set(

        @SerializedName("name")
        var name: String,

        @SerializedName("slug")
        var slug: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("descriptionHtml")
        var descriptionHtml: String

)
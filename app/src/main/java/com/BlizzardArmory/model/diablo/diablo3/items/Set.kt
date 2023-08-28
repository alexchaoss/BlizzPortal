package com.BlizzardArmory.model.diablo.diablo3.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Set.
 */
@Keep
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
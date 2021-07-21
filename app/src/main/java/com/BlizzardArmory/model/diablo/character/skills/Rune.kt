package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.SerializedName


/**
 * The type Rune.
 */
data class Rune(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("level")
    var level: Int,

    @SerializedName("description")
    var description: String,

    @SerializedName("descriptionHtml")
    var descriptionHtml: String

)
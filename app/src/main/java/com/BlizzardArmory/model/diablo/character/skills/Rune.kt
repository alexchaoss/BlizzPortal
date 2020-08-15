package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Rune.
 */
data class Rune(

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("type")
        @Expose
        var type: String,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("descriptionHtml")
        @Expose
        var descriptionHtml: String

)
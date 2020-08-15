package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Skill.
 */
data class Skill(

        @SerializedName("slug")
        @Expose
        var slug: String,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("icon")
        @Expose
        var icon: String,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("tooltipUrl")
        @Expose
        var tooltipUrl: String,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("descriptionHtml")
        @Expose
        var descriptionHtml: String,

        @SerializedName("flavorText")
        @Expose
        var flavorText: String

)
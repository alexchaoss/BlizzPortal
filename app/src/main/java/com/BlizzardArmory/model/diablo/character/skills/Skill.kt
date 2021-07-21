package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.SerializedName


/**
 * The type Skill.
 */
data class Skill(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("level")
    var level: Int,

    @SerializedName("tooltipUrl")
    var tooltipUrl: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("descriptionHtml")
    var descriptionHtml: String,

    @SerializedName("flavorText")
    var flavorText: String

)
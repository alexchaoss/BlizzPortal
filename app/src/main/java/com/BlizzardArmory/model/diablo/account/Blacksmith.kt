package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Blacksmith.
 */
data class Blacksmith(

    @SerializedName("slug")
    var slug: String,

    @SerializedName("level")
    var level: Int

)
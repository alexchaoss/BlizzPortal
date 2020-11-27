package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.SerializedName


/**
 * The type Attributes.
 */
data class Attributes(

        @SerializedName("primary")
        var primary: List<String>,

        @SerializedName("secondary")
        var secondary: List<String>

)
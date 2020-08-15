package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Attributes.
 */
data class Attributes(

        @SerializedName("primary")
        @Expose
        var primary: List<String>,

        @SerializedName("secondary")
        @Expose
        var secondary: List<String>

)
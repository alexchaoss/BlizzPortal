package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Attributes.
 */
data class Attributes(

        @SerializedName("other")
        @Expose
        val other: List<Any>,

        @SerializedName("primary")
        @Expose
        val primary: List<Primary>,

        @SerializedName("secondary")
        @Expose
        val secondary: List<Secondary>

)
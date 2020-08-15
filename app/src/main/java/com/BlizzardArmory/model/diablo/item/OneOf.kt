package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type One of.
 */
data class OneOf(

        @SerializedName("text")
        @Expose
        val text: String,


        @SerializedName("textHtml")
        @Expose
        val textHtml: String

)
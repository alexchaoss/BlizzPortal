package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Primary.
 */
data class Primary(

        @SerializedName("text")
        @Expose
        val text: String,


        @SerializedName("textHtml")
        @Expose
        val textHtml: String

)
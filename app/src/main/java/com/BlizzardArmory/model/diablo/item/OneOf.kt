package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.SerializedName


/**
 * The type One of.
 */
data class OneOf(

        @SerializedName("text")

        val text: String,


        @SerializedName("textHtml")

        val textHtml: String

)
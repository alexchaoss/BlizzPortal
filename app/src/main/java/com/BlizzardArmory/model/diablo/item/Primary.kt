package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.SerializedName


/**
 * The type Primary.
 */
data class Primary(

    @SerializedName("text")

    val text: String,


    @SerializedName("textHtml")

    val textHtml: String

)
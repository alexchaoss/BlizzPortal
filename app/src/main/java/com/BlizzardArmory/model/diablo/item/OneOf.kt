package com.BlizzardArmory.model.diablo.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type One of.
 */
@Keep
data class OneOf(

    @SerializedName("text")

    val text: String,


    @SerializedName("textHtml")

    val textHtml: String

)
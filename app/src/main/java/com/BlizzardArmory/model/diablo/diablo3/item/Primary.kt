package com.BlizzardArmory.model.diablo.diablo3.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Primary.
 */
@Keep
data class Primary(

    @SerializedName("text")

    val text: String,


    @SerializedName("textHtml")

    val textHtml: String

)
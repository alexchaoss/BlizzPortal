package com.BlizzardArmory.model.diablo.diablo3.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Secondary.
 */
@Keep
data class Secondary(

    @SerializedName("text")

    val text: String,

    @SerializedName("textHtml")

    val textHtml: String

)
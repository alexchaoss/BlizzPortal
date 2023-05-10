package com.BlizzardArmory.model.diablo.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Random affix.
 */
@Keep
data class RandomAffix(

    @SerializedName("oneOf")

    val oneOf: List<OneOf>
)
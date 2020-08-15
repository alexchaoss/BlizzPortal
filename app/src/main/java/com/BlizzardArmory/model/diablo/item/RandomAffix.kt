package com.BlizzardArmory.model.diablo.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Random affix.
 */
data class RandomAffix(

        @SerializedName("oneOf")
        @Expose
        val oneOf: List<OneOf>
)
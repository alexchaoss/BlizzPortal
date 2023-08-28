package com.BlizzardArmory.model.diablo.diablo3.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Attributes.
 */
@Keep
data class Attributes(

    @SerializedName("other")

    val other: List<Any>,

    @SerializedName("primary")

    val primary: List<Primary>,

    @SerializedName("secondary")

    val secondary: List<Secondary>
)
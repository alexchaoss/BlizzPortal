package com.BlizzardArmory.model.diablo.diablo3.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Attributes.
 */
@Keep
data class Attributes(

    @SerializedName("primary")
    var primary: List<String>,

    @SerializedName("secondary")
    var secondary: List<String>

)
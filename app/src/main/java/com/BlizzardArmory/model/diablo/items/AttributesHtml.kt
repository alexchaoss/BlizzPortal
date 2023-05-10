package com.BlizzardArmory.model.diablo.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Attributes html.
 */
@Keep
data class AttributesHtml(

    @SerializedName("primary")
    var primary: List<String>,

    @SerializedName("secondary")
    var secondary: List<String>

)
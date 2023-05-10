package com.BlizzardArmory.model.diablo.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Gem.
 */
@Keep
data class Gem(

    @SerializedName("item")
    var item: GemInfo,

    @SerializedName("attributes")
    var attributes: List<String>,

    @SerializedName("isGem")
    var isGem: Boolean,

    @SerializedName("isJewel")
    var isJewel: Boolean

)
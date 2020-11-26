package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.SerializedName


/**
 * The type Gem.
 */
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
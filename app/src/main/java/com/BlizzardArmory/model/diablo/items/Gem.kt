package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Gem.
 */
data class Gem(

        @SerializedName("item")
        @Expose
        var item: GemInfo,

        @SerializedName("attributes")
        @Expose
        var attributes: List<String>,

        @SerializedName("isGem")
        @Expose
        var isGem: Boolean,

        @SerializedName("isJewel")
        @Expose
        var isJewel: Boolean

)
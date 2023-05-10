package com.BlizzardArmory.model.diablo.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Dye color.
 */
@Keep
data class DyeColor(

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("tooltipParams")
    var tooltipParams: String

)
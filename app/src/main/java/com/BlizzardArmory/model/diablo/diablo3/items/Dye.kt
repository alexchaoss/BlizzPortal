package com.BlizzardArmory.model.diablo.diablo3.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Dye.
 */
@Keep
data class Dye(

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("tooltipParams")
    var tooltipParams: String

)
package com.BlizzardArmory.model.diablo.diablo3.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Right finger.
 */
@Keep
data class RightFinger(

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("displayColor")
    var displayColor: String,

    @SerializedName("tooltipParams")
    var tooltipParams: String

)
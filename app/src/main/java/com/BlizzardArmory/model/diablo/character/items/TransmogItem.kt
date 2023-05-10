package com.BlizzardArmory.model.diablo.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Transmog item.
 */
@Keep
data class TransmogItem(

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
package com.BlizzardArmory.model.diablo.diablo3.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Shoulders.
 */
@Keep
data class Shoulders(

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("displayColor")
    var displayColor: String,

    @SerializedName("tooltipParams")
    var tooltipParams: String,

    @SerializedName("transmogItem")
    var transmogItem: TransmogItem,

    @SerializedName("dyeColor")
    var dyeColor: DyeColor? = null
)
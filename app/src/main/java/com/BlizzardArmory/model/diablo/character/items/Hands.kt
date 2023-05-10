package com.BlizzardArmory.model.diablo.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Hands.
 */
@Keep
data class Hands(

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
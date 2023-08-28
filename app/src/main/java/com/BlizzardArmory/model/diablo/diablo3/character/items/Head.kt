package com.BlizzardArmory.model.diablo.diablo3.character.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Head.
 */
@Keep
data class Head(

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
    var transmogItem: com.BlizzardArmory.model.diablo.diablo3.character.items.TransmogItem,

    @SerializedName("dyeColor")
    var dyeColor: com.BlizzardArmory.model.diablo.diablo3.character.items.DyeColor? = null
)
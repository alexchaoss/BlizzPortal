package com.BlizzardArmory.model.diablo.character.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Torso.
 */
data class Torso(

        @SerializedName("id")
        @Expose
        var id: String,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("icon")
        @Expose
        var icon: String,

        @SerializedName("displayColor")
        @Expose
        var displayColor: String,

        @SerializedName("tooltipParams")
        @Expose
        var tooltipParams: String,

        @SerializedName("transmogItem")
        @Expose
        var transmogItem: TransmogItem,

        @SerializedName("dyeColor")
        @Expose
        var dyeColor: DyeColor? = null
)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Color.
 */
data class Color(

        @SerializedName("r")
        var r: Int,

        @SerializedName("g")
        var g: Int,

        @SerializedName("b")
        var b: Int,

        @SerializedName("a")
        var a: Float
)

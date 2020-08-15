package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Color.
 */
data class Color(

        @field:Expose
        @field:SerializedName("r")
        var r: Int,

        @field:Expose
        @field:SerializedName("g")
        var g: Int,

        @field:Expose
        @field:SerializedName("b")
        var b: Int,

        @field:Expose
        @field:SerializedName("a")
        var a: Float
)

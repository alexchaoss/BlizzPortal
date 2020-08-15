package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Fallen hero.
 */
data class FallenHero(
        @SerializedName("heroId")
        @Expose
        private var heroId: Long,

        @SerializedName("name")
        @Expose
        var name: String,


        @SerializedName("class")
        @Expose
        var class_: String,


        @SerializedName("level")
        @Expose
        var level: Int,


        @SerializedName("elites")
        @Expose
        var elites: Int,


        @SerializedName("hardcore")
        @Expose
        var hardcore: Boolean,


        @SerializedName("death")
        @Expose
        var death: Death,

        @SerializedName("gender")
        @Expose
        var gender: Int
)
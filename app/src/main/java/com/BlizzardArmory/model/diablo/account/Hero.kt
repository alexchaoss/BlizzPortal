package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Hero.
 */
data class Hero(

        @SerializedName("id")
        @Expose
        var id: Long,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("class")
        @Expose
        var class_: String,

        @SerializedName("classSlug")
        @Expose
        var classSlug: String,

        @SerializedName("gender")
        @Expose
        var gender: Int,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("kills")
        @Expose
        var kills: EliteKills,

        @SerializedName("paragonLevel")
        @Expose
        var paragonLevel: Int,

        @SerializedName("hardcore")
        @Expose
        var hardcore: Boolean,

        @SerializedName("seasonal")
        @Expose
        var seasonal: Boolean,

        @SerializedName("dead")
        @Expose
        var dead: Boolean,

        @SerializedName("last-updated")
        @Expose
        var lastUpdated: Long
)
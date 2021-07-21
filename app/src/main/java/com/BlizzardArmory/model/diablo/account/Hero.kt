package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Hero.
 */
data class Hero(

    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("class")
    var class_: String,

    @SerializedName("classSlug")
    var classSlug: String,

    @SerializedName("gender")
    var gender: Int,

    @SerializedName("level")
    var level: Int,

    @SerializedName("kills")
    var kills: EliteKills,

    @SerializedName("paragonLevel")
    var paragonLevel: Int,

    @SerializedName("hardcore")
    var hardcore: Boolean,

    @SerializedName("seasonal")
    var seasonal: Boolean,

    @SerializedName("dead")
    var dead: Boolean,

    @SerializedName("last-updated")
    var lastUpdated: Long
)
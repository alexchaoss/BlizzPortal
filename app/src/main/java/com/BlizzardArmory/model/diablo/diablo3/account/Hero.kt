package com.BlizzardArmory.model.diablo.diablo3.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Hero.
 */
@Keep
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
package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Fallen hero.
 */
@Keep
data class FallenHero(
    @SerializedName("heroId")
    var heroId: Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("class")
    var class_: String,
    @SerializedName("level")
    var level: Int,
    @SerializedName("elites")
    var elites: Int,
    @SerializedName("hardcore")
    var hardcore: Boolean,
    @SerializedName("death")
    var death: Death,
    @SerializedName("gender")
    var gender: Int
)
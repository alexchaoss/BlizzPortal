package com.BlizzardArmory.model.overwatch.topheroes

import com.google.gson.annotations.SerializedName


/**
 * The type Top hero.
 */
open class TopHero {

    @SerializedName("timePlayed")
    var timePlayed: String? = null

    @SerializedName("gamesWon")
    var gamesWon = 0.0

    @SerializedName("winPercentage")
    var winPercentage = 0.0

    @SerializedName("weaponAccuracy")
    var weaponAccuracy = 0.0

    @SerializedName("eliminationsPerLife")
    var eliminationsPerLife = 0f

    @SerializedName("multiKillBest")
    var multiKillBest = 0.0

    @SerializedName("objectiveKills")
    var objectiveKills = 0.0

    open fun getName(): String {
        return ""
    }
}
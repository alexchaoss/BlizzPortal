package com.BlizzardArmory.model.overwatch.account.heroes

import com.BlizzardArmory.model.overwatch.account.*
import com.google.gson.annotations.SerializedName


/**
 * The type Hero.
 */
open class Hero {

    @SerializedName("assists")
    var assists: Assists? = null

    @SerializedName("average")
    var average: Average? = null

    @SerializedName("best")
    var best: Best? = null

    @SerializedName("combat")
    var combat: Combat? = null

    @SerializedName("deaths")
    var deaths: Any? = null

    @SerializedName("heroSpecific")
    var heroSpecific: HeroSpecific? = null

    @SerializedName("game")
    var game: Game? = null

    @SerializedName("matchAwards")
    var matchAwards: MatchAwards? = null

    @SerializedName("miscellaneous")
    var miscellaneous: Miscellaneous? = null

    open fun getName(): String {
        return ""
    }

}
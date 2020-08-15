package com.BlizzardArmory.model.overwatch.heroes

import com.BlizzardArmory.model.overwatch.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Hero.
 */
open class Hero {

    @SerializedName("assists")
    @Expose
    var assists: Assists? = null

    @SerializedName("average")
    @Expose
    var average: Average? = null

    @SerializedName("best")
    @Expose
    var best: Best? = null

    @SerializedName("combat")
    @Expose
    var combat: Combat? = null

    @SerializedName("deaths")
    @Expose
    var deaths: Any? = null

    @SerializedName("heroSpecific")
    @Expose
    var heroSpecific: HeroSpecific? = null

    @SerializedName("game")
    @Expose
    var game: Game? = null

    @SerializedName("matchAwards")
    @Expose
    var matchAwards: MatchAwards? = null

    @SerializedName("miscellaneous")
    @Expose
    var miscellaneous: Miscellaneous? = null

}
package com.BlizzardArmory.model.overwatch.quickplay

import com.BlizzardArmory.model.overwatch.Awards
import com.BlizzardArmory.model.overwatch.CareerStats
import com.BlizzardArmory.model.overwatch.Games
import com.BlizzardArmory.model.overwatch.topheroes.TopHeroes
import com.google.gson.annotations.SerializedName


/**
 * The type Quick play stats.
 */
data class QuickPlayStats(

    @SerializedName("awards")
    var awards: Awards,

    @SerializedName("careerStats")
    var careerStats: CareerStats,

    @SerializedName("games")
    var games: Games,

    @SerializedName("topHeroes")
    var topHeroes: TopHeroes

)
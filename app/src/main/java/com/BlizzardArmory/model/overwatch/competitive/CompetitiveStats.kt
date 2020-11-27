package com.BlizzardArmory.model.overwatch.competitive

import com.BlizzardArmory.model.overwatch.Awards
import com.BlizzardArmory.model.overwatch.CareerStats
import com.BlizzardArmory.model.overwatch.Games
import com.BlizzardArmory.model.overwatch.topheroes.TopHeroes
import com.google.gson.annotations.SerializedName


/**
 * The type Competitive stats.
 */
data class CompetitiveStats(

        @SerializedName("awards")
        var awards: Awards,

        @SerializedName("careerStats")
        var careerStats: CareerStats,

        @SerializedName("games")
        var games: Games,

        @SerializedName("topHeroes")
        var topHeroes: TopHeroes

)
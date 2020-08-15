package com.BlizzardArmory.model.overwatch.quickplay

import com.BlizzardArmory.model.overwatch.Awards
import com.BlizzardArmory.model.overwatch.CareerStats
import com.BlizzardArmory.model.overwatch.Games
import com.BlizzardArmory.model.overwatch.topheroes.TopHeroes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Quick play stats.
 */
data class QuickPlayStats(

        @SerializedName("awards")
        @Expose
        var awards: Awards,

        @SerializedName("careerStats")
        @Expose
        var careerStats: CareerStats,

        @SerializedName("games")
        @Expose
        var games: Games,

        @SerializedName("topHeroes")
        @Expose
        var topHeroes: TopHeroes

)
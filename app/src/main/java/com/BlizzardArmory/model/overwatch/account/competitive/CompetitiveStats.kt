package com.BlizzardArmory.model.overwatch.account.competitive

import androidx.annotation.Keep
import com.BlizzardArmory.model.overwatch.account.Awards
import com.BlizzardArmory.model.overwatch.account.CareerStats
import com.BlizzardArmory.model.overwatch.account.Games
import com.BlizzardArmory.model.overwatch.account.topheroes.TopHeroes
import com.google.gson.annotations.SerializedName


/**
 * The type Competitive stats.
 */
@Keep
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
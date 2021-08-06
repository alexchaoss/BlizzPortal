package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


/**
 * The type Competitive stats.
 */
data class CompetitiveStats(

    @SerializedName("eliminationsAvg")
    var eliminationsAvg: Float,

    @SerializedName("damageDoneAvg")
    var damageDoneAvg: Double,

    @SerializedName("deathsAvg")
    var deathsAvg: Float,

    @SerializedName("finalBlowsAvg")
    var finalBlowsAvg: Float,

    @SerializedName("healingDoneAvg")
    var healingDoneAvg: Double,

    @SerializedName("objectiveKillsAvg")
    var objectiveKillsAvg: Float,

    @SerializedName("objectiveTimeAvg")
    var objectiveTimeAvg: String,

    @SerializedName("soloKillsAvg")
    var soloKillsAvg: Float,

    @SerializedName("games")
    var games: Games,

    @SerializedName("awards")
    var awards: Awards

)
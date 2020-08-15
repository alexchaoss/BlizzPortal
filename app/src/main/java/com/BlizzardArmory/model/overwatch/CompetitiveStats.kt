package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Competitive stats.
 */
data class CompetitiveStats(

        @SerializedName("eliminationsAvg")
        @Expose
        var eliminationsAvg: Float,

        @SerializedName("damageDoneAvg")
        @Expose
        var damageDoneAvg: Double,

        @SerializedName("deathsAvg")
        @Expose
        var deathsAvg: Float,

        @SerializedName("finalBlowsAvg")
        @Expose
        var finalBlowsAvg: Float,

        @SerializedName("healingDoneAvg")
        @Expose
        var healingDoneAvg: Double,

        @SerializedName("objectiveKillsAvg")
        @Expose
        var objectiveKillsAvg: Float,

        @SerializedName("objectiveTimeAvg")
        @Expose
        var objectiveTimeAvg: String,

        @SerializedName("soloKillsAvg")
        @Expose
        var soloKillsAvg: Float,

        @SerializedName("games")
        @Expose
        var games: Games,

        @SerializedName("awards")
        @Expose
        var awards: Awards

)
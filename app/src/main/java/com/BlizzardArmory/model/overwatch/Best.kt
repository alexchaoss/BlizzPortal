package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Best.
 */

data class Best(

        @SerializedName("allDamageDoneMostInGame")
        var allDamageDoneMostInGame: Double,

        @SerializedName("barrierDamageDoneMostInGame")
        var barrierDamageDoneMostInGame: Double,

        @SerializedName("defensiveAssistsMostInGame")
        var defensiveAssistsMostInGame: Double,

        @SerializedName("eliminationsMostInGame")
        var eliminationsMostInGame: Double,

        @SerializedName("environmentalKillsMostInGame")
        var environmentalKillsMostInGame: Double,

        @SerializedName("finalBlowsMostInGame")
        var finalBlowsMostInGame: Double,

        @SerializedName("healingDoneMostInGame")
        var healingDoneMostInGame: Double,

        @SerializedName("heroDamageDoneMostInGame")
        var heroDamageDoneMostInGame: Double,

        @SerializedName("killsStreakBest")
        var killsStreakBest: Double,

        @SerializedName("meleeFinalBlowsMostInGame")
        var meleeFinalBlowsMostInGame: Double,

        @SerializedName("multikillsBest")
        var multikillsBest: Double,

        @SerializedName("objectiveKillsMostInGame")
        var objectiveKillsMostInGame: Double,

        @SerializedName("objectiveTimeMostInGame")
        var objectiveTimeMostInGame: String,

        @SerializedName("offensiveAssistsMostInGame")
        var offensiveAssistsMostInGame: Double,

        @SerializedName("reconAssistsMostInGame")
        var reconAssistsMostInGame: Double,

        @SerializedName("soloKillsMostInGame")
        var soloKillsMostInGame: Double,

        @SerializedName("teleporterPadsDestroyedMostInGame")
        var teleporterPadsDestroyedMostInGame: Double,

        @SerializedName("timeSpentOnFireMostInGame")
        var timeSpentOnFireMostInGame: String,

        @SerializedName("turretsDestroyedMostInGame")
        var turretsDestroyedMostInGame: Double
) {
    val bestList: HashMap<String, String>
        get() {
            val formatter: NumberFormat = DecimalFormat("#0")
            val best = HashMap<String, String>()
            best["ALL DAMAGE DONE "] = formatter.format(allDamageDoneMostInGame)
            best["BARRIER DAMAGE DONE "] = formatter.format(barrierDamageDoneMostInGame)
            best["DEFENSIVE ASSIST - MOST IN GAME "] = formatter.format(defensiveAssistsMostInGame)
            best["ELEMINATIONS - MOST IN GAME "] = formatter.format(eliminationsMostInGame)
            best["ENVIRONMENTAL KILL - MOST IN GAME "] = formatter.format(environmentalKillsMostInGame)
            best["FINAL BLOWS - MOST IN GAME "] = formatter.format(finalBlowsMostInGame)
            best["HEALING DONE - MOST IN GAME "] = formatter.format(healingDoneMostInGame)
            best["HERO DAMAGE DONE - MOST IN GAME "] = formatter.format(heroDamageDoneMostInGame)
            best["KILL STREAK BEST "] = formatter.format(killsStreakBest)
            best["MELEE FINAL BLOWS - MOST IN GAME "] = formatter.format(meleeFinalBlowsMostInGame)
            best["MULTIKILL BEST "] = formatter.format(multikillsBest)
            best["OBJECTIVE KILLS - MOST IN GAME "] = formatter.format(objectiveKillsMostInGame)
            best["OBJECTIVE TIME - MOST IN GAME "] = objectiveTimeMostInGame
            best["OFFENSIVE ASSISTS - MOST IN GAME "] = formatter.format(offensiveAssistsMostInGame)
            best["RECON ASSISTS - MOST IN GAME "] = formatter.format(reconAssistsMostInGame)
            best["SOLO KILLS - MOST IN GAME "] = formatter.format(soloKillsMostInGame)
            best["TELEPORTER PADS DESTROYED - MOST IN GAME "] = formatter.format(teleporterPadsDestroyedMostInGame)
            best["TIME SPENT ON FIRE - MOST IN GAME "] = timeSpentOnFireMostInGame
            best["TURRETS DESTROYED - MOST IN GAME "] = formatter.format(turretsDestroyedMostInGame)
            return best
        }
}
package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Best.
 */
data class Best(

        @SerializedName("allDamageDoneMostInGame")
        @Expose
        var allDamageDoneMostInGame: Double,

        @SerializedName("barrierDamageDoneMostInGame")
        @Expose
        var barrierDamageDoneMostInGame: Double,

        @SerializedName("defensiveAssistsMostInGame")
        @Expose
        var defensiveAssistsMostInGame: Double,

        @SerializedName("eliminationsMostInGame")
        @Expose
        var eliminationsMostInGame: Double,

        @SerializedName("environmentalKillsMostInGame")
        @Expose
        var environmentalKillsMostInGame: Double,

        @SerializedName("finalBlowsMostInGame")
        @Expose
        var finalBlowsMostInGame: Double,

        @SerializedName("healingDoneMostInGame")
        @Expose
        var healingDoneMostInGame: Double,

        @SerializedName("heroDamageDoneMostInGame")
        @Expose
        var heroDamageDoneMostInGame: Double,

        @SerializedName("killsStreakBest")
        @Expose
        var killsStreakBest: Double,

        @SerializedName("meleeFinalBlowsMostInGame")
        @Expose
        var meleeFinalBlowsMostInGame: Double,

        @SerializedName("multikillsBest")
        @Expose
        var multikillsBest: Double,

        @SerializedName("objectiveKillsMostInGame")
        @Expose
        var objectiveKillsMostInGame: Double,

        @SerializedName("objectiveTimeMostInGame")
        @Expose
        var objectiveTimeMostInGame: String,

        @SerializedName("offensiveAssistsMostInGame")
        @Expose
        var offensiveAssistsMostInGame: Double,

        @SerializedName("reconAssistsMostInGame")
        @Expose
        var reconAssistsMostInGame: Double,

        @SerializedName("soloKillsMostInGame")
        @Expose
        var soloKillsMostInGame: Double,

        @SerializedName("teleporterPadsDestroyedMostInGame")
        @Expose
        var teleporterPadsDestroyedMostInGame: Double,

        @SerializedName("timeSpentOnFireMostInGame")
        @Expose
        var timeSpentOnFireMostInGame: String,

        @SerializedName("turretsDestroyedMostInGame")
        @Expose
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
package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Average.
 */

data class Average(

    @SerializedName("allDamageDoneAvgPer10Min")
    var allDamageDoneAvgPer10Min: Double,

    @SerializedName("barrierDamageDoneAvgPer10Min")
    var barrierDamageDoneAvgPer10Min: Double,

    @SerializedName("deathsAvgPer10Min")
    var deathsAvgPer10Min: Float,

    @SerializedName("eliminationsAvgPer10Min")
    var eliminationsAvgPer10Min: Float,

    @SerializedName("finalBlowsAvgPer10Min")
    var finalBlowsAvgPer10Min: Float,

    @SerializedName("healingDoneAvgPer10Min")
    var healingDoneAvgPer10Min: Double,

    @SerializedName("heroDamageDoneAvgPer10Min")
    var heroDamageDoneAvgPer10Min: Double,

    @SerializedName("objectiveKillsAvgPer10Min")
    var objectiveKillsAvgPer10Min: Float,

    @SerializedName("objectiveTimeAvgPer10Min")
    var objectiveTimeAvgPer10Min: String,

    @SerializedName("soloKillsAvgPer10Min")
    var soloKillsAvgPer10Min: Float,

    @SerializedName("timeSpentOnFireAvgPer10Min")
    var timeSpentOnFireAvgPer10Min: String
) {
    val average: HashMap<String, String>
        get() {
            val averageList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            averageList["ALL DAMAGE DONE - AVG PER 10 MIN"] = formatter.format(allDamageDoneAvgPer10Min)
            averageList["BARRIER DAMAGE DONE - AVR PER 10 MIN"] = formatter.format(barrierDamageDoneAvgPer10Min)
            averageList["DEATHS - AVG PER 10 MIN"] = deathsAvgPer10Min.toString()
            averageList["ELEMINATIONS - AVG PER 10 MIN"] = eliminationsAvgPer10Min.toString()
            averageList["FINAL BLOWS - AVG PER 10 MIN"] = finalBlowsAvgPer10Min.toString()
            averageList["HEALING DONE - AVG PER 10 MIN"] = formatter.format(healingDoneAvgPer10Min)
            averageList["HERO DAMAGE DONE - AVG PER 10 MIN"] = formatter.format(heroDamageDoneAvgPer10Min)
            averageList["OBJECTIVE KILLS - AVG PER 10 MIN"] = objectiveKillsAvgPer10Min.toString()
            averageList["OBJECTIVE TIME - AVG PER 10 MIN"] = objectiveTimeAvgPer10Min
            averageList["SOLO KILLS - AVG PER 10 MIN"] = soloKillsAvgPer10Min.toString()
            averageList["TIME SPENT ON FIRE - AVG PER 10 MIN"] = timeSpentOnFireAvgPer10Min
            return averageList
        }
}
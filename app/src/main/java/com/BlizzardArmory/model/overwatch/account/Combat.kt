package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Combat.
 */

data class Combat(

    @SerializedName("barrierDamageDone")
    var barrierDamageDone: Double,

    @SerializedName("damageDone")
    var damageDone: Double,

    @SerializedName("deaths")
    var deaths: Double,

    @SerializedName("eliminations")
    var eliminations: Double,

    @SerializedName("environmentalKills")
    var environmentalKills: Double,

    @SerializedName("finalBlows")
    var finalBlows: Double,

    @SerializedName("heroDamageDone")
    var heroDamageDone: Double,

    @SerializedName("meleeFinalBlows")
    var meleeFinalBlows: Double,

    @SerializedName("multikills")
    var multikills: Double,

    @SerializedName("objectiveKills")
    var objectiveKills: Double,

    @SerializedName("objectiveTime")
    var objectiveTime: String,

    @SerializedName("soloKills")
    var soloKills: Double,

    @SerializedName("timeSpentOnFire")
    var timeSpentOnFire: String
) {
    val combat: HashMap<String, String>
        get() {
            val combatList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            combatList["BARRIER DAMAGE DONE"] = formatter.format(barrierDamageDone)
            combatList["DAMAGE DONE"] = formatter.format(damageDone)
            combatList["DEATHS"] = formatter.format(deaths)
            combatList["ELIMINATIONS"] = formatter.format(eliminations)
            combatList["ENVIRONMENTAL KILLS"] = formatter.format(environmentalKills)
            combatList["FINAL BLOWS"] = formatter.format(finalBlows)
            combatList["HERO DAMAGE DONE"] = formatter.format(heroDamageDone)
            combatList["MELEE FINAL BLOWS"] = formatter.format(meleeFinalBlows)
            combatList["MULTIKILLS"] = formatter.format(multikills)
            combatList["OBJECTIVE KILLS"] = formatter.format(objectiveKills)
            combatList["OBJECTIVE TIME"] = objectiveTime
            combatList["SOLO KILLS"] = formatter.format(soloKills)
            combatList["TIME SPENT ON FIRE"] = timeSpentOnFire
            return combatList
        }
}
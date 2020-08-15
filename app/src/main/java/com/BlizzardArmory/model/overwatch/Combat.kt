package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Combat.
 */
data class Combat(

        @SerializedName("barrierDamageDone")
        @Expose
        var barrierDamageDone: Double,

        @SerializedName("damageDone")
        @Expose
        var damageDone: Double,

        @SerializedName("deaths")
        @Expose
        var deaths: Double,

        @SerializedName("eliminations")
        @Expose
        var eliminations: Double,

        @SerializedName("environmentalKills")
        @Expose
        var environmentalKills: Double,

        @SerializedName("finalBlows")
        @Expose
        var finalBlows: Double,

        @SerializedName("heroDamageDone")
        @Expose
        var heroDamageDone: Double,

        @SerializedName("meleeFinalBlows")
        @Expose
        var meleeFinalBlows: Double,

        @SerializedName("multikills")
        @Expose
        var multikills: Double,

        @SerializedName("objectiveKills")
        @Expose
        var objectiveKills: Double,

        @SerializedName("objectiveTime")
        @Expose
        var objectiveTime: String,

        @SerializedName("soloKills")
        @Expose
        var soloKills: Double,

        @SerializedName("timeSpentOnFire")
        @Expose
        var timeSpentOnFire: String
) {
    val combat: HashMap<String, String?>
        get() {
            val combatList = HashMap<String, String?>()
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
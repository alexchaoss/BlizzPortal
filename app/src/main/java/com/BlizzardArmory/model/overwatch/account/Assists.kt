package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Assists.
 */

data class Assists(

    @SerializedName("defensiveAssists")
    var defensiveAssists: Double,

    @SerializedName("healingDone")
    var healingDone: Double,

    @SerializedName("offensiveAssists")
    var offensiveAssists: Double,

    @SerializedName("reconAssists")
    var reconAssists: Double
) {
    val assists: HashMap<String, String>
        get() {
            val assistList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            assistList["DEFENSIVE ASSISTS"] = formatter.format(defensiveAssists)
            assistList["HEALING DONE"] = formatter.format(healingDone)
            assistList["OFFENSIVE ASSISTS"] = formatter.format(offensiveAssists)
            assistList["RECON ASSISTS"] = formatter.format(reconAssists)
            return assistList
        }
}
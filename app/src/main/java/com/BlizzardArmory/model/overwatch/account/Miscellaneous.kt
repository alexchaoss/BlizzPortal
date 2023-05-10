package com.BlizzardArmory.model.overwatch.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Miscellaneous.
 */

@Keep
data class Miscellaneous(

    @SerializedName("teleporterPadsDestroyed")
    var teleporterPadsDestroyed: Double,

    @SerializedName("turretsDestroyed")
    var turretsDestroyed: Double
) {
    val misc: HashMap<String, String>
        get() {
            val miscList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            miscList["TELEPORTER PADS DESTROYED"] = formatter.format(teleporterPadsDestroyed)
            miscList["TURRETS DESTROYED"] = formatter.format(turretsDestroyed)
            return miscList
        }
}
package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Miscellaneous.
 */
data class Miscellaneous(

        @SerializedName("teleporterPadsDestroyed")
        @Expose
        var teleporterPadsDestroyed: Double,

        @SerializedName("turretsDestroyed")
        @Expose
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
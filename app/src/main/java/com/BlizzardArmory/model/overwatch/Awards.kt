package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Awards.
 */
data class Awards(

        @SerializedName("cards")
        @Expose
        var cards: Double,

        @SerializedName("medals")
        @Expose
        var medals: Double,

        @SerializedName("medalsBronze")
        @Expose
        var medalsBronze: Double,

        @SerializedName("medalsSilver")
        @Expose
        var medalsSilver: Double,

        @SerializedName("medalsGold")
        @Expose
        var medalsGold: Double
) {
    val awards: HashMap<String, String>
        get() {
            val awardsList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            awardsList["CARDS"] = formatter.format(cards)
            awardsList["MEDALS"] = formatter.format(medals)
            awardsList["MEDALS - GOLD"] = formatter.format(medalsGold)
            awardsList["MEDALS - SILVER"] = formatter.format(medalsSilver)
            awardsList["MEDALS - BRONZE"] = formatter.format(medalsBronze)
            return awardsList
        }
}
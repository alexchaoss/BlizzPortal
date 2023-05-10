package com.BlizzardArmory.model.overwatch.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Awards.
 */

@Keep
data class Awards(

    @SerializedName("cards")
    var cards: Double,

    @SerializedName("medals")
    var medals: Double,

    @SerializedName("medalsBronze")
    var medalsBronze: Double,

    @SerializedName("medalsSilver")
    var medalsSilver: Double,

    @SerializedName("medalsGold")
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
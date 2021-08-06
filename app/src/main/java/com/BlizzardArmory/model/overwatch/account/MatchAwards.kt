package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * The type Match awards.
 */

data class MatchAwards(
    @SerializedName("cards")
    var cards: Double,

    @SerializedName("medals")
    var medals: Double,

    @SerializedName("medalsBronze")
    var medalsBronze: Double,

    @SerializedName("medalsGold")
    var medalsGold: Double,

    @SerializedName("medalsSilver")
    var medalsSilver: Double
) {
    val match: HashMap<String, String>
        get() {
            val matchList = HashMap<String, String>()
            val formatter: NumberFormat = DecimalFormat("#0")
            matchList["CARDS"] = formatter.format(cards)
            matchList["MEDALS"] = formatter.format(medals)
            matchList["MEDALS - GOLD"] = formatter.format(medalsGold)
            matchList["MEDALS - SILVER"] = formatter.format(medalsSilver)
            matchList["MEDALS - BRONZE"] = formatter.format(medalsBronze)
            return matchList
        }
}
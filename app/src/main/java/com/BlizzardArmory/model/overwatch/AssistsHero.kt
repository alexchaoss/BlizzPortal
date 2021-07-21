package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.SerializedName


/**
 * The type Assists hero.
 */
data class AssistsHero(

    @SerializedName("defensiveAssists")
    var defensiveAssists: Double,

    @SerializedName("defensiveAssistsAvgPer10Min")
    var defensiveAssistsAvgPer10Min: Float,

    @SerializedName("defensiveAssistsMostInGame")
    var defensiveAssistsMostInGame: Double,

    @SerializedName("healingDone")
    var healingDone: Double,

    @SerializedName("healingDoneAvgPer10Min")
    var healingDoneAvgPer10Min: Double,

    @SerializedName("healingDoneMostInGame")
    var healingDoneMostInGame: Double,

    @SerializedName("offensiveAssists")
    var offensiveAssists: Double,

    @SerializedName("offensiveAssistsAvgPer10Min")
    var offensiveAssistsAvgPer10Min: Float,

    @SerializedName("offensiveAssistsMostInGame")
    var offensiveAssistsMostInGame: Double

)
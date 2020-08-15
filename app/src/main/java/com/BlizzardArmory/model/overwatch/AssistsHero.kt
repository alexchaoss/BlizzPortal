package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Assists hero.
 */
data class AssistsHero(

        @SerializedName("defensiveAssists")
        @Expose
        var defensiveAssists: Double,

        @SerializedName("defensiveAssistsAvgPer10Min")
        @Expose
        var defensiveAssistsAvgPer10Min: Float,

        @SerializedName("defensiveAssistsMostInGame")
        @Expose
        var defensiveAssistsMostInGame: Double,

        @SerializedName("healingDone")
        @Expose
        var healingDone: Double,

        @SerializedName("healingDoneAvgPer10Min")
        @Expose
        var healingDoneAvgPer10Min: Double,

        @SerializedName("healingDoneMostInGame")
        @Expose
        var healingDoneMostInGame: Double,

        @SerializedName("offensiveAssists")
        @Expose
        var offensiveAssists: Double,

        @SerializedName("offensiveAssistsAvgPer10Min")
        @Expose
        var offensiveAssistsAvgPer10Min: Float,

        @SerializedName("offensiveAssistsMostInGame")
        @Expose
        var offensiveAssistsMostInGame: Double

)
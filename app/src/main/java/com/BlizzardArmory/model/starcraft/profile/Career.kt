package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Career.
 */
data class Career(

        @SerializedName("terranWins")
        @Expose
        var terranWins: Int,

        @SerializedName("zergWins")
        @Expose
        var zergWins: Int,

        @SerializedName("protossWins")
        @Expose
        var protossWins: Int,

        @SerializedName("totalCareerGames")
        @Expose
        var totalCareerGames: Int,

        @SerializedName("totalGamesThisSeason")
        @Expose
        var totalGamesThisSeason: Int,

        @SerializedName("current1v1LeagueName")
        @Expose
        var current1v1LeagueName: String,

        @SerializedName("currentBestTeamLeagueName")
        @Expose
        var currentBestTeamLeagueName: Any,

        @SerializedName("best1v1Finish")
        @Expose
        var best1v1Finish: Best1v1Finish,

        @SerializedName("bestTeamFinish")
        @Expose
        var bestTeamFinish: BestTeamFinish

)
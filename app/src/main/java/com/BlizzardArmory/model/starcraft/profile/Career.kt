package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Career.
 */
data class Career(

    @SerializedName("terranWins")
    var terranWins: Int,

    @SerializedName("zergWins")
    var zergWins: Int,

    @SerializedName("protossWins")
    var protossWins: Int,

    @SerializedName("totalCareerGames")
    var totalCareerGames: Int,

    @SerializedName("totalGamesThisSeason")
    var totalGamesThisSeason: Int,

    @SerializedName("current1v1LeagueName")
    var current1v1LeagueName: String,

    @SerializedName("currentBestTeamLeagueName")
    var currentBestTeamLeagueName: Any,

    @SerializedName("best1v1Finish")
    var best1v1Finish: Best1v1Finish,

    @SerializedName("bestTeamFinish")
    var bestTeamFinish: BestTeamFinish

)
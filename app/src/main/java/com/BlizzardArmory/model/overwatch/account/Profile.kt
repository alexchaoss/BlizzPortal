package com.BlizzardArmory.model.overwatch.account

import androidx.annotation.Keep
import com.BlizzardArmory.model.overwatch.account.competitive.CompetitiveStats
import com.BlizzardArmory.model.overwatch.account.quickplay.QuickPlayStats
import com.google.gson.annotations.SerializedName


/**
 * The type Profile.
 */
@Keep
data class Profile(
    @SerializedName("icon")
    var icon: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("level")
    var level: Int,

    @SerializedName("levelIcon")
    var levelIcon: String,

    @SerializedName("prestige")
    var prestige: Int,

    @SerializedName("prestigeIcon")
    var prestigeIcon: String,

    @SerializedName("endorsement")
    var endorsement: Int,

    @SerializedName("endorsementIcon")
    var endorsementIcon: String,

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("ratingIcon")
    var ratingIcon: String,

    @SerializedName("ratings")
    var ratings: List<Rating>,

    @SerializedName("gamesWon")
    var gamesWon: Int,

    @SerializedName("quickPlayStats")
    var quickPlayStats: QuickPlayStats,

    @SerializedName("competitiveStats")
    var competitiveStats: CompetitiveStats

)
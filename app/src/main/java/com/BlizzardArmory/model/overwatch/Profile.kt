package com.BlizzardArmory.model.overwatch

import com.BlizzardArmory.model.overwatch.competitive.CompetitiveStats
import com.BlizzardArmory.model.overwatch.quickplay.QuickPlayStats
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Profile.
 */
data class Profile(
        @SerializedName("icon")
        @Expose
        var icon: String,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("levelIcon")
        @Expose
        var levelIcon: String,

        @SerializedName("prestige")
        @Expose
        var prestige: Int,

        @SerializedName("prestigeIcon")
        @Expose
        var prestigeIcon: String,

        @SerializedName("endorsement")
        @Expose
        var endorsement: Int,

        @SerializedName("endorsementIcon")
        @Expose
        var endorsementIcon: String,

        @SerializedName("rating")
        @Expose
        var rating: Int,

        @SerializedName("ratingIcon")
        @Expose
        var ratingIcon: String,

        @SerializedName("ratings")
        @Expose
        var ratings: List<Rating>,

        @SerializedName("gamesWon")
        @Expose
        var gamesWon: Int,

        @SerializedName("quickPlayStats")
        @Expose
        var quickPlayStats: QuickPlayStats,

        @SerializedName("competitiveStats")
        @Expose
        var competitiveStats: CompetitiveStats

)
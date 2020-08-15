package com.BlizzardArmory.model.overwatch.quickplay

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Quick play.
 */
data class QuickPlay(

        @SerializedName("quickPlayStats")
        @Expose
        var quickPlayStats: QuickPlayStats,

        @SerializedName("rating")
        @Expose
        var rating: Int,

        @SerializedName("ratingIcon")
        @Expose
        var ratingIcon: String,

        @SerializedName("ratings")
        @Expose
        var ratings: Any

)
package com.BlizzardArmory.model.overwatch.quickplay

import com.google.gson.annotations.SerializedName


/**
 * The type Quick play.
 */
data class QuickPlay(

    @SerializedName("quickPlayStats")
    var quickPlayStats: QuickPlayStats,

    @SerializedName("rating")
    var rating: Int,

    @SerializedName("ratingIcon")
    var ratingIcon: String,

    @SerializedName("ratings")
    var ratings: Any

)
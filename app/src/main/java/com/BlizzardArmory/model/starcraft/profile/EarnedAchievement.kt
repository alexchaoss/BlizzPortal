package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Earned achievement.
 */
data class EarnedAchievement(

        @SerializedName("achievementId")
        @Expose
        var achievementId: String,

        @SerializedName("completionDate")
        @Expose
        var completionDate: Long,

        @SerializedName("numCompletedAchievementsInSeries")
        @Expose
        var numCompletedAchievementsInSeries: Int,

        @SerializedName("totalAchievementsInSeries")
        @Expose
        var totalAchievementsInSeries: Int,

        @SerializedName("isComplete")
        @Expose
        var isIsComplete: Boolean,

        @SerializedName("inProgress")
        @Expose
        var isInProgress: Boolean,

        @SerializedName("criteria")
        @Expose
        var criteria: List<Criterium>

)
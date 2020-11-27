package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Earned achievement.
 */
data class EarnedAchievement(

        @SerializedName("achievementId")
        var achievementId: String,

        @SerializedName("completionDate")
        var completionDate: Long,

        @SerializedName("numCompletedAchievementsInSeries")
        var numCompletedAchievementsInSeries: Int,

        @SerializedName("totalAchievementsInSeries")
        var totalAchievementsInSeries: Int,

        @SerializedName("isComplete")
        var isIsComplete: Boolean,

        @SerializedName("inProgress")
        var isInProgress: Boolean,

        @SerializedName("criteria")
        var criteria: List<Criterium>

)
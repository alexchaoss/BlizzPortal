package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Profile.
 */
data class Profile(

        @SerializedName("summary")
        @Expose
        var summary: Summary,

        @SerializedName("snapshot")
        @Expose
        var snapshot: Snapshot,

        @SerializedName("career")
        @Expose
        var career: Career,

        @SerializedName("swarmLevels")
        @Expose
        var swarmLevels: SwarmLevels,

        @SerializedName("campaign")
        @Expose
        var campaign: Campaign,

        @SerializedName("categoryPointProgress")
        @Expose
        var categoryPointProgress: List<CategoryPointProgress>,

        @SerializedName("achievementShowcase")
        @Expose
        var achievementShowcase: List<String>,

        @SerializedName("earnedRewards")
        @Expose
        var earnedRewards: List<EarnedReward>,

        @SerializedName("earnedAchievements")
        @Expose
        var earnedAchievements: List<EarnedAchievement>

)
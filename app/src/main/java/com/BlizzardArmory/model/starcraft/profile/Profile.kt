package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Profile.
 */
data class Profile(

    @SerializedName("summary")
    var summary: Summary,

    @SerializedName("snapshot")
    var snapshot: Snapshot,

    @SerializedName("career")
    var career: Career,

    @SerializedName("swarmLevels")
    var swarmLevels: SwarmLevels,

    @SerializedName("campaign")
    var campaign: Campaign,

    @SerializedName("categoryPointProgress")
    var categoryPointProgress: List<CategoryPointProgress>,

    @SerializedName("achievementShowcase")
    var achievementShowcase: List<String>,

    @SerializedName("earnedRewards")
    var earnedRewards: List<EarnedReward>,

    @SerializedName("earnedAchievements")
    var earnedAchievements: List<EarnedAchievement>

)
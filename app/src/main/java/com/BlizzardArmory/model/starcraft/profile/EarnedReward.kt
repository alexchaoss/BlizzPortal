package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Earned reward.
 */
data class EarnedReward(

        @SerializedName("rewardId")
        @Expose
        var rewardId: String,

        @SerializedName("achievementId")
        @Expose
        var achievementId: String,

        @SerializedName("selected")
        @Expose
        var isSelected: Boolean

)
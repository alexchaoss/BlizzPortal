package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Earned reward.
 */
data class EarnedReward(

        @SerializedName("rewardId")
        var rewardId: String,

        @SerializedName("achievementId")
        var achievementId: String,

        @SerializedName("selected")
        var isSelected: Boolean

)
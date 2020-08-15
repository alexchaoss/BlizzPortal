package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Campaign.
 */
data class Campaign(

        @SerializedName("difficultyCompleted")
        @Expose
        var difficultyCompleted: DifficultyCompleted

)
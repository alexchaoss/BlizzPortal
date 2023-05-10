package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Campaign.
 */
@Keep
data class Campaign(

    @SerializedName("difficultyCompleted")
    var difficultyCompleted: DifficultyCompleted

)
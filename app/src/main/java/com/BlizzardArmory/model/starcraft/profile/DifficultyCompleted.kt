package com.BlizzardArmory.model.starcraft.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Difficulty completed.
 */
@Keep
data class DifficultyCompleted(

    @SerializedName("wings-of-liberty")
    var wingsOfLiberty: String?,

    @SerializedName("heart-of-the-swarm")
    var heartOfTheSwarm: String?,

    @SerializedName("legacy-of-the-void")
    var legacyOfTheVoid: String?

)
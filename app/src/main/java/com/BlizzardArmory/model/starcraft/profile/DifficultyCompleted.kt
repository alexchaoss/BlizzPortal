package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Difficulty completed.
 */
data class DifficultyCompleted(

        @SerializedName("wings-of-liberty")
        @Expose
        var wingsOfLiberty: String,

        @SerializedName("heart-of-the-swarm")
        @Expose
        var heartOfTheSwarm: String,

        @SerializedName("legacy-of-the-void")
        @Expose
        var legacyOfTheVoid: String

)
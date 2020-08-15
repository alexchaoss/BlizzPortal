package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Zerg.
 */
class Zerg(

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("maxLevelPoints")
        @Expose
        var maxLevelPoints: Int,

        @SerializedName("currentLevelPoints")
        @Expose
        var currentLevelPoints: Int

)
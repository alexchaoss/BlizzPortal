package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Swarm levels.
 */
data class SwarmLevels(

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("terran")
        @Expose
        var terran: Terran,

        @SerializedName("zerg")
        @Expose
        var zerg: Zerg,

        @SerializedName("protoss")
        @Expose
        var protoss: Protoss

)
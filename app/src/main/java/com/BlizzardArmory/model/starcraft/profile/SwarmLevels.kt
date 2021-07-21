package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Swarm levels.
 */
data class SwarmLevels(

    @SerializedName("level")
    var level: Int,

    @SerializedName("terran")
    var terran: Terran,

    @SerializedName("zerg")
    var zerg: Zerg,

    @SerializedName("protoss")
    var protoss: Protoss

)
package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Zerg.
 */
class Zerg(

    @SerializedName("level")
    var level: Int,

    @SerializedName("maxLevelPoints")
    var maxLevelPoints: Int,

    @SerializedName("currentLevelPoints")
    var currentLevelPoints: Int

)
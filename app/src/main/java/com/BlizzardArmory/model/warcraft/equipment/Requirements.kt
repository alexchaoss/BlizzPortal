package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Requirements.
 */
@Keep
data class Requirements(

    @SerializedName("level")
    var level: Level,

    @SerializedName("faction")
    var faction: Faction

)
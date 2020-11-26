package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Requirements.
 */
data class Requirements(

        @SerializedName("level")
        var level: Level,

        @SerializedName("faction")
        var faction: Faction

)
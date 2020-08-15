package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Requirements.
 */
data class Requirements(

        @SerializedName("level")
        @Expose
        var level: Level,

        @SerializedName("faction")
        @Expose
        var faction: Faction

)
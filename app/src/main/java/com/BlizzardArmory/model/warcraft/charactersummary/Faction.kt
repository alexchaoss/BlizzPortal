package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.SerializedName


/**
 * The type Faction.
 */
data class Faction(

        @SerializedName("type")
        var type: String,

        @SerializedName("name")
        var name: String

)
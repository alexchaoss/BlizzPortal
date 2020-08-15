package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Raid progression.
 */
data class RaidProgression(

        @SerializedName("href")
        @Expose
        var href: String

)
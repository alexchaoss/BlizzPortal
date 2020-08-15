package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Talent tier.
 */
data class TalentTier(

        @SerializedName("level")
        @Expose
        var level: Int,

        @SerializedName("talents")
        @Expose
        var talents: List<Talent>
)
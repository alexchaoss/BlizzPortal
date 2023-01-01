package com.BlizzardArmory.model.warcraft.talentold.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Talent tier.
 */
data class TalentTier(

    @SerializedName("level")
    var level: Int,

    @SerializedName("talents")
    var talents: List<Talent>
)
package com.BlizzardArmory.model.warcraft.talentold.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Gender description.
 */
data class GenderDescription(

    @SerializedName("male")
    var male: String,

    @SerializedName("female")
    var female: String

)
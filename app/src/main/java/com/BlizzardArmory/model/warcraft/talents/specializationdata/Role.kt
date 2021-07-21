package com.BlizzardArmory.model.warcraft.talents.specializationdata

import com.google.gson.annotations.SerializedName


/**
 * The type Role.
 */
data class Role(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String
)
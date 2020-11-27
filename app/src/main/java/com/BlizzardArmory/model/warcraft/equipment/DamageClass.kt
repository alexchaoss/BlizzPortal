package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Damage class.
 */
data class DamageClass(

        @SerializedName("type")
        var type: String,

        @SerializedName("name")
        var name: String

)
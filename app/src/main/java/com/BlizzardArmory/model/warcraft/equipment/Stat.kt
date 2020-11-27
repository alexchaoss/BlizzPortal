package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Stat.
 */
data class Stat(

        @SerializedName("type")
        var type: Type,

        @SerializedName("value")
        var value: Int,

        @SerializedName("display_string")
        var display_string: String,

        @SerializedName("display")
        var display: Display,

        @SerializedName("is_negated")
        var isIsNegated: Boolean,

        @SerializedName("is_equip_bonus")
        var isIsEquipBonus: Boolean

)
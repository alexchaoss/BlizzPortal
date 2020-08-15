package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Stat.
 */
data class Stat(

        @SerializedName("type")
        @Expose
        var type: Type,

        @SerializedName("value")
        @Expose
        var value: Int,

        @SerializedName("display_string")
        @Expose
        var display_string: String,

        @SerializedName("display")
        @Expose
        var display: Display,

        @SerializedName("is_negated")
        @Expose
        var isIsNegated: Boolean,

        @SerializedName("is_equip_bonus")
        @Expose
        var isIsEquipBonus: Boolean

)
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Damage.
 */
data class Damage(

        @SerializedName("min_value")
        @Expose
        var minValue: Double,

        @SerializedName("max_value")
        @Expose
        var maxValue: Double,

        @SerializedName("display_string")
        @Expose
        var displayString: String,

        @SerializedName("damage_class")
        @Expose
        var damageClass: DamageClass

)
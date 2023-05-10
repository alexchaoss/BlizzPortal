package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Damage.
 */
@Keep
data class Damage(

    @SerializedName("min_value")
    var minValue: Double,

    @SerializedName("max_value")
    var maxValue: Double,

    @SerializedName("display_string")
    var displayString: String,

    @SerializedName("damage_class")
    var damageClass: DamageClass

)
package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.common.Spell
import com.google.gson.annotations.SerializedName


/**
 * The type Spell description.
 */
@Keep
data class SpellDescription(

    @SerializedName("spell")
    var spell: Spell,

    @SerializedName("description")
    var description: String,

    @SerializedName("display_color")
    var color: Color?

)
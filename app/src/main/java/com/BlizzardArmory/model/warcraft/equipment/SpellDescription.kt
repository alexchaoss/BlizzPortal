package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Spell description.
 */
data class SpellDescription(

    @SerializedName("spell")
    var spell: Spell,

    @SerializedName("description")
    var description: String,

    @SerializedName("display_color")
    var color: Color

)
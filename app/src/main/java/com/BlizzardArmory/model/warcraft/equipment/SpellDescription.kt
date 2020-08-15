package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Spell description.
 */
data class SpellDescription(

        @SerializedName("spell")
        @Expose
        var spell: Spell,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("display_color")
        @Expose
        var color: Color

)
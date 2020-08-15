package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Skills.
 */
data class Skills(

        @SerializedName("active")
        @Expose
        var active: List<Active>,

        @SerializedName("passive")
        @Expose
        var passive: List<Passive>

)
package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Active.
 */
data class Active(

        @SerializedName("skill")
        @Expose
        var skill: Skill,

        @SerializedName("rune")
        @Expose
        var rune: Rune

)
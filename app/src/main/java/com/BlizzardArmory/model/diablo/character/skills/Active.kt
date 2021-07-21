package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.SerializedName


/**
 * The type Active.
 */
data class Active(

    @SerializedName("skill")
    var skill: Skill,

    @SerializedName("rune")
    var rune: Rune

)
package com.BlizzardArmory.model.diablo.diablo3.character.skills

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Active.
 */
@Keep
data class Active(

    @SerializedName("skill")
    var skill: Skill,

    @SerializedName("rune")
    var rune: Rune?

)
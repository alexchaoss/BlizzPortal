package com.BlizzardArmory.model.diablo.character.skills

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Passive.
 */
@Keep
data class Passive(

    @SerializedName("skill") var skill: Skill

)
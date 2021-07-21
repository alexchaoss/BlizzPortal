package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.SerializedName


/**
 * The type Passive.
 */
data class Passive(

    @SerializedName("skill") var skill: Skill? = null

)
package com.BlizzardArmory.model.diablo.character.skills

import com.google.gson.annotations.SerializedName


/**
 * The type Skills.
 */
data class Skills(

    @SerializedName("active")
    var active: List<Active>,

    @SerializedName("passive")
    var passive: List<Passive>

)
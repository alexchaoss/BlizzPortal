package com.BlizzardArmory.model.diablo.diablo3.character.skills

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Skills.
 */
@Keep
data class Skills(

    @SerializedName("active")
    var active: List<Active>,

    @SerializedName("passive")
    var passive: List<Passive>

)
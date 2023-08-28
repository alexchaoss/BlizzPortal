package com.BlizzardArmory.model.diablo.diablo3.character.progression

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Progression.
 */
@Keep
data class Progression(

    @SerializedName("act1")
    var act1: Act1,

    @SerializedName("act2")
    var act2: Act2,

    @SerializedName("act3")
    var act3: Act3,

    @SerializedName("act4")
    var act4: Act4,

    @SerializedName("act5")
    var act5: Act5

)
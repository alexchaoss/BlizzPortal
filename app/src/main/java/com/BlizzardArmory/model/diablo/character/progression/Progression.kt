package com.BlizzardArmory.model.diablo.character.progression

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Progression.
 */
data class Progression(

        @SerializedName("act1")
        @Expose
        var act1: Act1,

        @SerializedName("act2")
        @Expose
        var act2: Act2,

        @SerializedName("act3")
        @Expose
        var act3: Act3,

        @SerializedName("act4")
        @Expose
        var act4: Act4,

        @SerializedName("act5")
        @Expose
        var act5: Act5

)
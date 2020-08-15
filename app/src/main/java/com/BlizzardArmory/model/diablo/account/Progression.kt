package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Progression.
 */
data class Progression(

        @SerializedName("act1")
        @Expose
        var act1: Boolean,

        @SerializedName("act3")
        @Expose
        var act3: Boolean,

        @SerializedName("act2")
        @Expose
        var act2: Boolean,

        @SerializedName("act5")
        @Expose
        var act5: Boolean,

        @SerializedName("act4")
        @Expose
        var act4: Boolean

)
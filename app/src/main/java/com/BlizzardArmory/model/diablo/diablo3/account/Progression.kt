package com.BlizzardArmory.model.diablo.diablo3.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Progression.
 */
@Keep
data class Progression(

    @SerializedName("act1")
    var act1: Boolean,

    @SerializedName("act3")
    var act3: Boolean,

    @SerializedName("act2")
    var act2: Boolean,

    @SerializedName("act5")
    var act5: Boolean,

    @SerializedName("act4")
    var act4: Boolean

)
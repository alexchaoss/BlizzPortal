package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.SerializedName


/**
 * The type Progression.
 */
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
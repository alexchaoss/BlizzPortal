package com.BlizzardArmory.model.diablo.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Time played.
 */
@Keep
data class TimePlayed(

    @SerializedName("demon-hunter")
    var demonHunter: Double,

    @SerializedName("barbarian")
    var barbarian: Double,

    @SerializedName("witch-doctor")
    var witchDoctor: Double,

    @SerializedName("necromancer")
    var necromancer: Double,

    @SerializedName("wizard")
    var wizard: Double,

    @SerializedName("monk")
    var monk: Double,

    @SerializedName("crusader")
    var crusader: Double

)
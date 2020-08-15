package com.BlizzardArmory.model.diablo.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Time played.
 */
data class TimePlayed(

        @SerializedName("demon-hunter")
        @Expose
        var demonHunter: Double,

        @SerializedName("barbarian")
        @Expose
        var barbarian: Double,

        @SerializedName("witch-doctor")
        @Expose
        var witchDoctor: Double,

        @SerializedName("necromancer")
        @Expose
        var necromancer: Double,

        @SerializedName("wizard")
        @Expose
        var wizard: Double,

        @SerializedName("monk")
        @Expose
        var monk: Double,

        @SerializedName("crusader")
        @Expose
        var crusader: Double

)
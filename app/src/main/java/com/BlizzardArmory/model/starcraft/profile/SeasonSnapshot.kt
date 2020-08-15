package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Season snapshot.
 */
data class SeasonSnapshot(
        @SerializedName("1v1")
        @Expose
        var oneVone: OneVOne,

        @SerializedName("2v2")
        @Expose
        var twoVtwo: TwoVTwo,

        @SerializedName("3v3")
        @Expose
        var threeVthree: ThreeVThree,

        @SerializedName("4v4")
        @Expose
        var fourVfour: FourVFour,

        @SerializedName("Archon")
        @Expose
        var archon: Archon

)
package com.BlizzardArmory.model.starcraft.profile

import com.google.gson.annotations.SerializedName


/**
 * The type Season snapshot.
 */
data class SeasonSnapshot(
        @SerializedName("1v1")
        var oneVone: OneVOne,

        @SerializedName("2v2")
        var twoVtwo: TwoVTwo,

        @SerializedName("3v3")
        var threeVthree: ThreeVThree,

        @SerializedName("4v4")
        var fourVfour: FourVFour,

        @SerializedName("Archon")
        var archon: Archon

)
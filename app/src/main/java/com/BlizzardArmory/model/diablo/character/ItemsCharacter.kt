package com.BlizzardArmory.model.diablo.character

import com.BlizzardArmory.model.diablo.character.items.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Items character.
 */
data class ItemsCharacter(

        @SerializedName("head")
        @Expose
        var head: Head,

        @SerializedName("neck")
        @Expose
        var neck: Neck,

        @SerializedName("torso")
        @Expose
        var torso: Torso,

        @SerializedName("shoulders")
        @Expose
        var shoulders: Shoulders,

        @SerializedName("legs")
        @Expose
        var legs: Legs,

        @SerializedName("waist")
        @Expose
        var waist: Waist,

        @SerializedName("hands")
        @Expose
        var hands: Hands,

        @SerializedName("bracers")
        @Expose
        var bracers: Bracers,

        @SerializedName("feet")
        @Expose
        var feet: Feet,

        @SerializedName("leftFinger")
        @Expose
        var leftFinger: LeftFinger,

        @SerializedName("rightFinger")
        @Expose
        var rightFinger: RightFinger,

        @SerializedName("mainHand")
        @Expose
        var mainHand: MainHand,

        @SerializedName("offHand")
        @Expose
        var offHand: OffHand

)
package com.BlizzardArmory.model.diablo.character

import com.BlizzardArmory.model.diablo.character.items.*
import com.google.gson.annotations.SerializedName


/**
 * The type Items character.
 */
data class ItemsCharacter(

    @SerializedName("head")
    var head: Head,

    @SerializedName("neck")
    var neck: Neck,

    @SerializedName("torso")
    var torso: Torso,

    @SerializedName("shoulders")
    var shoulders: Shoulders,

    @SerializedName("legs")
    var legs: Legs,

    @SerializedName("waist")
    var waist: Waist,

    @SerializedName("hands")
    var hands: Hands,

    @SerializedName("bracers")
    var bracers: Bracers,

    @SerializedName("feet")
    var feet: Feet,

    @SerializedName("leftFinger")
    var leftFinger: LeftFinger,

    @SerializedName("rightFinger")
    var rightFinger: RightFinger,

    @SerializedName("mainHand")
    var mainHand: MainHand,

    @SerializedName("offHand")
    var offHand: OffHand

)
package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.SerializedName


/**
 * The type Type.
 */
data class Type(

    @SerializedName("twoHanded")
    var twoHanded: Boolean,

    @SerializedName("oneHanded")
    var oneHanded: Boolean,

    @SerializedName("id")
    var id: String

)
package com.BlizzardArmory.model.diablo.items

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Type.
 */
@Keep
data class Type(

    @SerializedName("twoHanded")
    var twoHanded: Boolean,

    @SerializedName("oneHanded")
    var oneHanded: Boolean,

    @SerializedName("id")
    var id: String

)
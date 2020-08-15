package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Type.
 */
data class Type(

        @SerializedName("twoHanded")
        @Expose
        var twoHanded: Boolean,

        @SerializedName("oneHanded")
        @Expose
        var oneHanded: Boolean,

        @SerializedName("id")
        @Expose
        var id: String

)
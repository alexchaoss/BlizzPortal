package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Gender.
 */
data class Gender(

        @SerializedName("type")
        @Expose
        var type: String,

        @SerializedName("name")
        @Expose
        var name: String

)
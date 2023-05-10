package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Gender.
 */
@Keep
data class Gender(

    @SerializedName("type")
    var type: String,

    @SerializedName("name")
    var name: String

)
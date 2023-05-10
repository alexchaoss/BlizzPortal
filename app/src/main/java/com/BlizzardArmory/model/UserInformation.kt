package com.BlizzardArmory.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type User information.
 */
@Keep
data class UserInformation(

    @SerializedName("sub")
    val sub: String,

    @SerializedName("id")
    val userID: String,

    @SerializedName("battletag")
    val battleTag: String

)
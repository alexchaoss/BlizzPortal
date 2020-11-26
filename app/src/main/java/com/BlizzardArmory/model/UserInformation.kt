package com.BlizzardArmory.model

import com.google.gson.annotations.SerializedName


/**
 * The type User information.
 */
data class UserInformation(

        @SerializedName("sub")
        val sub: String,

        @SerializedName("id")
        val userID: String,

        @SerializedName("battletag")
        val battleTag: String

)
package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type User.
 */
@Keep
data class User(

    @SerializedName("href")
    var href: String

)
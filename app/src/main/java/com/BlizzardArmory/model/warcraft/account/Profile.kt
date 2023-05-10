package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Profile.
 */
@Keep
data class Profile(

    @SerializedName("href")
    var href: String

)
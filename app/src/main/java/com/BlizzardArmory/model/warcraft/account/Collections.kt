package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Collections.
 */
@Keep
data class Collections(

    @SerializedName("href")
    var href: String

)
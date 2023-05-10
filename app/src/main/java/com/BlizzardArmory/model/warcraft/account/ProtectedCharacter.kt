package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Protected character.
 */
@Keep
data class ProtectedCharacter(

    @SerializedName("href")
    var href: String

)
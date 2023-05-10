package com.BlizzardArmory.model.warcraft.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Character.
 */
@Keep
data class CharacterSelf(

    @SerializedName("href")
    var href: String

)
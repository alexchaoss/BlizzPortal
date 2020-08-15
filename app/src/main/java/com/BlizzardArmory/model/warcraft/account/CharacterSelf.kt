package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Character.
 */
data class CharacterSelf(

        @SerializedName("href")
        @Expose
        var href: String

)
package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Key.
 */
data class Key(

        @SerializedName("href")
        @Expose
        var href: String

)
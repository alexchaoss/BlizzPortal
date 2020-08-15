package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Collections.
 */
data class Collections(

        @SerializedName("href")
        @Expose
        var href: String

)
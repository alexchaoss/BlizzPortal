package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Titles.
 */
data class Titles(

        @SerializedName("href")
        @Expose
        var href: String

)
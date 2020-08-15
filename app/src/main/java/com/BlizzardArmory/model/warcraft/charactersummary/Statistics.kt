package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Statistics.
 */
data class Statistics(

        @SerializedName("href")
        @Expose
        var href: String

)
package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Pvp summary.
 */
@Keep
data class PvpSummary(

    @SerializedName("href")
    var href: String
)
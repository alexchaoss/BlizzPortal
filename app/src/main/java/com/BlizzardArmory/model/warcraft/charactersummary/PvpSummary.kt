package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.SerializedName


/**
 * The type Pvp summary.
 */
data class PvpSummary(

    @SerializedName("href")
    var href: String
)
package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Raid progression.
 */
@Keep
data class RaidProgression(

    @SerializedName("href")
    var href: String

)
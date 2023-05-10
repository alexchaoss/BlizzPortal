package com.BlizzardArmory.model.warcraft.charactersummary

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Achievements.
 */
@Keep
data class Achievements(

    @SerializedName("href")
    var href: String

)